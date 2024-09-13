package com.Stock.api_stock_emazon.adapters.driving.http.mapper;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddArticuloRequest;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddCategoryRequest;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloRequestMapper {

    @Mapping(target = "id", ignore = true)
    Articulo toArticulo(AddArticuloRequest request);
    List<Category> toCategories(List<Category> categories);
}
