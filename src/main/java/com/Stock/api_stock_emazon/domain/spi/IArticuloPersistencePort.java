package com.Stock.api_stock_emazon.domain.spi;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import com.Stock.api_stock_emazon.domain.model.Articulo;

import java.util.List;
import java.util.Optional;

public interface IArticuloPersistencePort {
    Optional<Articulo> getById(Long id);
    void saveArticulo(Articulo articulo);
    List<Articulo> getAllArticulos(int page, int size, String sortBy, String sortDirection);
    boolean existsByNombre(String nombre);
    Optional<ArticuloEntity> getByNombre(String nombre);

}
