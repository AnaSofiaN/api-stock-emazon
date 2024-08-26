package com.Stock.api_stock_emazon.domain.spi;

import com.Stock.api_stock_emazon.domain.model.Marca;

import java.util.List;

public interface IMarcaPersistencePort {
    Marca saveBrand(Marca marca);
    List<Marca> getAllBrands(Integer page, Integer size, String sortBy, String sortDirection);
    boolean existsByName(String name);
}
