package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.HistorialSuministroEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ISuministroEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IHistorialSuministroRepository;
import com.Stock.api_stock_emazon.domain.model.HistorialSuministro;
import com.Stock.api_stock_emazon.domain.spi.IHistorialSuministroPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuministroAdapter implements IHistorialSuministroPersistencePort {

    private final IHistorialSuministroRepository suministroRepository;
    private final ISuministroEntityMapper suministroEntityMapper;

    @Override
    public void saveHistorial(HistorialSuministro historialSuministro) {
        HistorialSuministroEntity historialEntity = suministroEntityMapper.toEntity(historialSuministro);
        suministroRepository.save(historialEntity);
    }
}
