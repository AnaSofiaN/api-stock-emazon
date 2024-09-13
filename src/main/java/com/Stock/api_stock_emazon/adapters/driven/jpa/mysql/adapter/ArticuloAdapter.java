package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IArticuloEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IArticuloRepository;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.spi.IArticuloPersistencePort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticuloAdapter implements IArticuloPersistencePort {

    private final IArticuloRepository articuloRepository;
    private final IArticuloEntityMapper articuloEntityMapper;

    @Override
    public void saveArticulo(Articulo articulo) {
        ArticuloEntity articuloEntity = articuloEntityMapper.toEntity(articulo);
        articuloRepository.save(articuloEntity);
    }

    @Override
    public List<Articulo> getAllArticulos(int page, int size, String sortBy, String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size,
                Sort.Direction.fromString(sortDirection), sortBy);
        return articuloRepository.findAll(pageRequest)
                .stream()
                .map(articuloEntityMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return articuloRepository.existsByNombre(nombre);
    }

    @Override
    public Optional<ArticuloEntity> getByNombre(String nombre) {
        // Buscar el artículo por nombre en el repositorio
        Optional<ArticuloEntity> articuloEntityOptional = articuloRepository.findByNombre(nombre);

        // Verificar si se encontró un artículo y devolverlo, o lanzar una excepción si no se encontró
        return articuloEntityOptional;
    }


}
