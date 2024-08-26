package com.Stock.api_stock_emazon.adapters.driving.http.mapper;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddCategoryRequest;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddMarcaRequest;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.model.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMarcaRequestMapper {
    @Mapping(target = "id", ignore = true)
    Marca toMarca(AddMarcaRequest request);
}
