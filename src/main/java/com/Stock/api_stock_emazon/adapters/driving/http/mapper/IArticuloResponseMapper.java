package com.Stock.api_stock_emazon.adapters.driving.http.mapper;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.ArticuloResponse;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.CategoryResponse;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloResponseMapper {
    ArticuloResponse toArticuloResponse(Articulo articulo);
    List<ArticuloResponse> toPageArticuloResponse(List<Articulo> articulo);
}
