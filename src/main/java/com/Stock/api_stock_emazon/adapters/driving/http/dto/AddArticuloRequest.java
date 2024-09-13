package com.Stock.api_stock_emazon.adapters.driving.http.dto;

import com.Stock.api_stock_emazon.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class AddArticuloRequest {
    private String nombre;
    private String descripcion;
    private int cantidad;
    private BigDecimal precio;
    private List<Category> categorias;
}
