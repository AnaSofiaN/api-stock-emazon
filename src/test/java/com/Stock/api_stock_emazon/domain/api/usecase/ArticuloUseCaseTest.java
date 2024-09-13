package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.ArticuloEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IArticuloEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IArticuloRepository;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.Stock.api_stock_emazon.domain.exception.ArticuloAsociationException;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.spi.IArticuloPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import com.Stock.api_stock_emazon.domain.util.ErrorConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticuloUseCaseTest {

    @InjectMocks
    private ArticuloUseCase articuloUseCase;

    @Mock
    private IArticuloPersistencePort articuloPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private IArticuloRepository articuloRepository;

    @Mock
    private IArticuloEntityMapper articuloEntityMapper;

    @Mock
    private ICategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateArticulo_Success() {
        // Preparar datos de entrada
        Category category1 = new Category(1L, "Category 1","category");
        Category category2 = new Category(2L, "Category 2","category1");
        List<Category> categories = Arrays.asList(category1, category2);
        List<ArticuloEntity> articulos = new ArrayList<>();

        Articulo articulo = new Articulo(1L, "Articulo 1", "Descripción 1", 10, new BigDecimal("100.00"), categories);

        // Configurar mocks
        when(articuloRepository.findByNombre(articulo.getNombre())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toModelListEntity(categories)).thenReturn(Arrays.asList(
                new CategoryEntity(1L, "Category 1","category",articulos),
                new CategoryEntity(2L, "Category 2","category2",articulos)
        ));

        // Ejecutar el caso de uso
        articuloUseCase.createArticulo(articulo);

        // Verificar que se guardó el artículo
        verify(articuloRepository, times(1)).save(any(ArticuloEntity.class));
    }

    @Test
    void testCreateArticulo_FailsWithTooManyCategories() {
        // Preparar datos de entrada con más de 3 categorías
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1","descripcion"),
                new Category(2L, "Category 2","descripcion1"),
                new Category(3L, "Category 3","descripcion2"),
                new Category(4L, "Category 4","descripcion3")
        );

        Articulo articulo = new Articulo(1L, "Articulo 1", "Descripción 1", 10, new BigDecimal("100.00"), categories);

        // Ejecutar y validar que lanza la excepción esperada
        ArticuloAsociationException exception = assertThrows(
                ArticuloAsociationException.class,
                () -> articuloUseCase.createArticulo(articulo)
        );

        assertEquals(ErrorConstants.LIMIT_CATEGORY_ARTICULO, exception.getMessage());
        verify(articuloRepository, never()).save(any(ArticuloEntity.class));
    }

    @Test
    void testCreateArticulo_FailsWithDuplicateCategories() {
        // Preparar datos de entrada con categorías duplicadas
        Category category1 = new Category(1L, "Category 1","descripcion");
        Category category2 = new Category(1L, "Category 1","descripcion"); // Categoría duplicada
        List<Category> categories = Arrays.asList(category1, category2);

        Articulo articulo = new Articulo(1L, "Articulo 1", "Descripción 1", 10, new BigDecimal("100.00"), categories);

        // Ejecutar y validar que lanza la excepción esperada
        ArticuloAsociationException exception = assertThrows(
                ArticuloAsociationException.class,
                () -> articuloUseCase.createArticulo(articulo)
        );

        assertEquals(ErrorConstants.LIMIT_RELATIONAL_CATEGORY_ARTICULO, exception.getMessage());
        verify(articuloRepository, never()).save(any(ArticuloEntity.class));
    }
}
