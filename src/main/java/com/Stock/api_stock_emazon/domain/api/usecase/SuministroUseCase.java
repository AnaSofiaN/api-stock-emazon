package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.domain.api.ISuministroServicePort;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.model.HistorialSuministro;
import com.Stock.api_stock_emazon.domain.spi.IArticuloPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.IHistorialSuministroPersistencePort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SuministroUseCase implements ISuministroServicePort {

    private final IArticuloPersistencePort articuloPersistencePort;
    private final IHistorialSuministroPersistencePort historialSuministroPersistencePort;

    @Transactional
    @Override
    public void agregarSuministro(Long articuloId, int cantidad, Long usuarioId) {
        // Obtener el artículo por ID
        Articulo articulo = articuloPersistencePort.getById(articuloId)
                .orElseThrow(() -> new EntityNotFoundException("Artículo no encontrado con ID: " + articuloId));

        // Aumentar la cantidad en stock del artículo
        articulo.setCantidad(articulo.getCantidad() + cantidad);
        articuloPersistencePort.saveArticulo(articulo);

        // Registrar el suministro en el historial
        HistorialSuministro historial = new HistorialSuministro();
        historial.setArticuloId(articuloId);
        historial.setCantidadSuministrada(cantidad);
        historial.setFechaSuministro(LocalDateTime.now());
        historial.setUsuarioId(usuarioId);
        historialSuministroPersistencePort.saveHistorial(historial);
    }
}
