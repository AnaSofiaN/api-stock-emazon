package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.MarcaEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IMarcaEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IMarcaRepository;
import com.Stock.api_stock_emazon.domain.model.Marca;
import com.Stock.api_stock_emazon.domain.spi.IMarcaPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MarcaAdapter implements IMarcaPersistencePort {
    private final IMarcaRepository marcaRepository;
    private final IMarcaEntityMapper marcaEntityMapper;
    @Override
    public Marca saveBrand(Marca marca) {
        MarcaEntity marcaEntity = marcaEntityMapper.toEntity(marca);
        marcaRepository.save(marcaEntity);
        return marca;
    }

    @Override
    public List<Marca> getAllBrands(Integer page, Integer size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
        List<MarcaEntity> marcaEntities = marcaRepository.findAll(pageable).getContent();
        return marcaEntityMapper.toModelList(marcaEntities);
    }

    @Override
    public boolean existsByName(String name) {
        Optional<MarcaEntity> marcaEntity = marcaRepository.findByNombre(name);
        return marcaEntity.isPresent();
    }
}
