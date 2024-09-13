package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloEntityMapper {
    @Mapping(source = "categorias", target = "categorias")
    ArticuloEntity toEntity(Articulo articulo);
    @Mapping(source = "categorias", target = "categorias")
    Articulo toModel(ArticuloEntity articuloEntity);
    List<Articulo> toModelList(List<ArticuloEntity> articulo);

}
