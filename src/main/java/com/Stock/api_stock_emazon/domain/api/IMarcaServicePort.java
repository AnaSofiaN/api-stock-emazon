package com.Stock.api_stock_emazon.domain.api;

import com.Stock.api_stock_emazon.domain.model.Marca;

import java.util.List;

public interface IMarcaServicePort {
    void createBrand(Marca marca);
    List<Marca> getAllBrands(int page, int size, String sortBy, String sortDirection);
}
