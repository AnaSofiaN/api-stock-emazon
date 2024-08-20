package com.Stock.api_stock_emazon.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private BigDecimal precio;
    private List<String> categorias;

    // Método para actualizar la cantidad de artículos en stock
    public void actualizarCantidad(int cantidad) {
        this.cantidad += cantidad;
    }

    // Método para calcular el valor total de los artículos en stock
    public BigDecimal calcularValorTotal() {
        return this.precio.multiply(new BigDecimal(this.cantidad));
    }
}

