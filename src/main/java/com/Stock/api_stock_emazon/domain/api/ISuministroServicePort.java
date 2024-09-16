package com.Stock.api_stock_emazon.domain.api;

public interface ISuministroServicePort {
    void agregarSuministro(Long articuloId, int cantidad, Long usuarioId);
}
