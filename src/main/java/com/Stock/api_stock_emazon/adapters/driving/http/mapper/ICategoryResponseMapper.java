package com.Stock.api_stock_emazon.adapters.driving.http.mapper;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.CategoryResponse;
import com.Stock.api_stock_emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
