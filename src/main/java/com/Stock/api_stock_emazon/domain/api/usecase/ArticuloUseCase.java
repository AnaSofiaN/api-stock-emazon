package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IArticuloEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IArticuloRepository;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.Stock.api_stock_emazon.domain.api.IArticuloServicePort;
import com.Stock.api_stock_emazon.domain.exception.ArticuloAsociationException;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.spi.IArticuloPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import com.Stock.api_stock_emazon.domain.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticuloUseCase implements IArticuloServicePort {

    private final IArticuloPersistencePort articuloPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IArticuloRepository articuloRepository;
    private final IArticuloEntityMapper articuloEntityMapper;
    private final ICategoryRepository categoryRepository;

    @Override
    public void createArticulo(Articulo articulo) {
        // Validar la cantidad de categorías asociadas
        if (articulo.getCategorias().size() < 1 || articulo.getCategorias().size() > 3) {
            throw new ArticuloAsociationException(ErrorConstants.LIMIT_CATEGORY_ARTICULO);
        }

        // Validar categorías repetidas
        Set<Category> uniqueCategories = new HashSet<>(articulo.getCategorias());
        if (uniqueCategories.size() < articulo.getCategorias().size()) {
            throw new ArticuloAsociationException(ErrorConstants.LIMIT_RELATIONAL_CATEGORY_ARTICULO);
        }

        // Verificar si el artículo ya existe
        Optional<ArticuloEntity> existingArticuloEntity = articuloRepository.findByNombre(articulo.getNombre());

        ArticuloEntity articuloEntity;
        if (existingArticuloEntity.isPresent()) {
            // Si el artículo existe, lo obtenemos de la base de datos
            articuloEntity = existingArticuloEntity.get();
            // Actualizar las categorías existentes
            List<CategoryEntity> existingCategories = articuloEntity.getCategorias();
            List<CategoryEntity> newCategories = categoryEntityMapper.toModelListEntity(articulo.getCategorias());
            Set<CategoryEntity> allCategories = new HashSet<>(existingCategories);
            allCategories.addAll(newCategories);
            articuloEntity.setCategorias(new ArrayList<>(allCategories));
        } else {
            // Si no existe, creamos un nuevo artículo
            articuloEntity = new ArticuloEntity();
            articuloEntity.setNombre(articulo.getNombre());
            articuloEntity.setDescripcion(articulo.getDescripcion());
            articuloEntity.setCantidad(articulo.getCantidad());
            articuloEntity.setPrecio(articulo.getPrecio());
            List<CategoryEntity> categoriasEntities = categoryEntityMapper.toModelListEntity(articulo.getCategorias());
            articuloEntity.setCategorias(categoriasEntities);
        }

        // Guardar el artículo con las nuevas categorías
        articuloRepository.save(articuloEntity);
    }

    @Override
    public List<Articulo> listarArticulos(int page, int size, String sortField, String sortDirection) {
        // Configurar la dirección del ordenamiento (ascendente o descendente)
        Sort.Direction direction = "asc".equalsIgnoreCase(sortDirection) ? Sort.Direction.ASC : Sort.Direction.DESC;

        // Crear el objeto Pageable
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        // Obtener los artículos desde el repositorio con paginación
        List<ArticuloEntity> articuloEntities = articuloRepository.findAllWithCategories(pageable);

        // Convertir las entidades a objetos de dominio
        return articuloEntityMapper.toModelList(articuloEntities);
    }


        public boolean existRelation(Articulo articulo, Category category) {
        return articuloRepository.existsRelation(articulo.getId(), category.getId());
    }
}
