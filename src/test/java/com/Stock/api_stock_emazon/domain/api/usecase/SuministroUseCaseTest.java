package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.model.HistorialSuministro;
import com.Stock.api_stock_emazon.domain.spi.IArticuloPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.IHistorialSuministroPersistencePort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SuministroUseCaseTest {

    @Mock
    private IArticuloPersistencePort articuloPersistencePort;

    @Mock
    private IHistorialSuministroPersistencePort historialSuministroPersistencePort;

    @InjectMocks
    private SuministroUseCase suministroUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agregarSuministro_successful() {
        // Arrange
        Long articuloId = 1L;
        int cantidad = 10;
        Long usuarioId = 2L;

        Articulo articulo = new Articulo();
        articulo.setId(articuloId);
        articulo.setCantidad(5);  // cantidad inicial

        when(articuloPersistencePort.getById(articuloId)).thenReturn(Optional.of(articulo));

        // Act
        suministroUseCase.agregarSuministro(articuloId, cantidad, usuarioId);

        // Assert
        verify(articuloPersistencePort, times(1)).saveArticulo(articulo);
        ArgumentCaptor<HistorialSuministro> historialCaptor = ArgumentCaptor.forClass(HistorialSuministro.class);
        verify(historialSuministroPersistencePort, times(1)).saveHistorial(historialCaptor.capture());

        HistorialSuministro historial = historialCaptor.getValue();
        assertEquals(articuloId, historial.getArticuloId());
        assertEquals(cantidad, historial.getCantidadSuministrada());
        assertEquals(usuarioId, historial.getUsuarioId());
        // Puede ser útil verificar también la fecha, pero es más complicado
    }

    @Test
    void agregarSuministro_articuloNotFound() {
        // Arrange
        Long articuloId = 1L;
        int cantidad = 10;
        Long usuarioId = 2L;

        when(articuloPersistencePort.getById(articuloId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> suministroUseCase.agregarSuministro(articuloId, cantidad, usuarioId));
        verify(articuloPersistencePort, never()).saveArticulo(any(Articulo.class));
        verify(historialSuministroPersistencePort, never()).saveHistorial(any(HistorialSuministro.class));
    }
}
