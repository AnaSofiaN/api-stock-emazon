package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.MarcaEntity;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.model.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaEntityMapper {
    MarcaEntity toEntity(Marca brand);
    Marca toDomain(MarcaEntity brandEntity);
    List<Marca> toModelList(List<MarcaEntity> marcaEntities);
}
