package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.Stock.api_stock_emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toModel(CategoryEntity categoryEntity);
    List<Category> toModelList(List<CategoryEntity> categoryEntities);
}
