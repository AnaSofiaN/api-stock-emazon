package com.Stock.api_stock_emazon.domain.spi;

import com.Stock.api_stock_emazon.domain.model.HistorialSuministro;

public interface IHistorialSuministroPersistencePort {
    void saveHistorial(HistorialSuministro historial);
}
