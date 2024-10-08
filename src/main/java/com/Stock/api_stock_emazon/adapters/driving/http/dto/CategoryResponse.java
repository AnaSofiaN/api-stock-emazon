package com.Stock.api_stock_emazon.adapters.driving.http.dto;

import com.Stock.api_stock_emazon.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String nombre;
    private String descripcion;
}
