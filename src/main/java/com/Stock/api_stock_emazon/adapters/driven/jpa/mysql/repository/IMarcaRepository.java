package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMarcaRepository extends JpaRepository<MarcaEntity, Long> {
}
