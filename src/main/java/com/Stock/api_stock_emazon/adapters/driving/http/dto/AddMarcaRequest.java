package com.Stock.api_stock_emazon.adapters.driving.http.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMarcaRequest {
    private String nombre;
    private String descripcion;
}
