package com.Stock.api_stock_emazon.domain.model;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_suministros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialSuministro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long articuloId;

    @Column(nullable = false)
    private int cantidadSuministrada;

    @Column(nullable = false)
    private LocalDateTime fechaSuministro;

    @Column(nullable = false)
    private Long usuarioId; // ID del auxiliar de bodega que realizó el suministro
}
