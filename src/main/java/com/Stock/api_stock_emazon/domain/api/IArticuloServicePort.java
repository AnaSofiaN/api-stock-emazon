package com.Stock.api_stock_emazon.domain.api;

import com.Stock.api_stock_emazon.domain.model.Articulo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticuloServicePort {
    void createArticulo(Articulo articulo);
    List<Articulo> listarArticulos(int page, int size, String sortField, String sortDirection);
}
