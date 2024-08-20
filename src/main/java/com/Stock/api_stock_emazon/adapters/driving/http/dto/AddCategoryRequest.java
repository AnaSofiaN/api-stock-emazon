package com.Stock.api_stock_emazon.adapters.driving.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AddCategoryRequest {
    private String nombre;
    private String descripcion;
}