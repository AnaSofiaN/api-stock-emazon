package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.HistorialSuministroEntity;
import com.Stock.api_stock_emazon.domain.model.HistorialSuministro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISuministroEntityMapper {

    HistorialSuministroEntity toEntity(HistorialSuministro historialSuministro);

    HistorialSuministro toModel(HistorialSuministroEntity historialSuministroEntity);
}
