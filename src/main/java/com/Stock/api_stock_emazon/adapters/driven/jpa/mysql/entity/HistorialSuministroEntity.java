package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_suministro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialSuministroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "articulo_id", nullable = false)
    private ArticuloEntity articulo;

    private int cantidadSumada;
    private LocalDateTime fechaSuministro;
    private String usuario;  // Nombre del auxiliar que realizó el suministro
}

