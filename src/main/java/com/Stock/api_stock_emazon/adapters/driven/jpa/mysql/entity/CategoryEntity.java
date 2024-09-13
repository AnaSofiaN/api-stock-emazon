package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity;

import com.Stock.api_stock_emazon.domain.model.Articulo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @ManyToMany(mappedBy = "categorias")
    private List<ArticuloEntity> articulos;
}
