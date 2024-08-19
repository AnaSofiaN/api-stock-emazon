package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "articulos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private BigDecimal precio;
    @ManyToMany
    private List<CategoryEntity> categorias;
}
