package com.Stock.api_stock_emazon.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {
    private Long id;
    private String nombre;
    private String descripcion;

    // Método para actualizar el nombre de la marca
    public void actualizarNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    // Método para actualizar la descripción de la marca
    public void actualizarDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }
}

