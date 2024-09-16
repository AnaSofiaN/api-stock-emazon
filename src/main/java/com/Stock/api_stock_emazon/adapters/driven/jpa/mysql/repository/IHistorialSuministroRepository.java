package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.HistorialSuministroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHistorialSuministroRepository extends JpaRepository<HistorialSuministroEntity, Long> {
    List<HistorialSuministroEntity> findByArticuloId(Long articuloId);
}

