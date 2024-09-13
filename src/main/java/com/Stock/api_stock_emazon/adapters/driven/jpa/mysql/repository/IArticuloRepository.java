package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IArticuloRepository extends JpaRepository<ArticuloEntity,Long> {
    Optional<ArticuloEntity> findByNombre(String name);
    boolean existsByNombre(String nombre);
    // Método para verificar si ya existe una relación entre un artículo y una categoría
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ArticuloEntity a JOIN a.categorias c " +
            "WHERE a.id = :articuloId AND c.id = :categoryId")
    boolean existsRelation(@Param("articuloId") Long articuloId, @Param("categoryId") Long categoryId);

    @Query("SELECT a FROM ArticuloEntity a LEFT JOIN FETCH a.categorias c")
    List<ArticuloEntity> findAllWithCategories(Pageable pageable);
}
