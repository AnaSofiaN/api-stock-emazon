package com.Stock.api_stock_emazon.adapters.driving.http.mapper;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.CategoryResponse;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.MarcaResponse;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.model.Marca;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaResponseMapper {
    MarcaResponse toMarcaResponse(Marca marca);
    List<MarcaResponse> toMarcaResponseList(List<Marca> marcas);
}
