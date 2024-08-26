package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.domain.exception.CategoryAlreadyExistsException;
import com.Stock.api_stock_emazon.domain.exception.EmptyOrNullException;
import com.Stock.api_stock_emazon.domain.exception.LimitCharactersException;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setNombre("Category 1");
        category.setDescripcion("Category de prueba");
    }

    @Test
    void testCreateCategory() {
        when(categoryPersistencePort.categoryExistsByName(anyString())).thenReturn(false);

        Category createCategory = categoryUseCase.createCategory(category);

        assertNotNull(createCategory);
        assertEquals(category.getNombre(), createCategory.getNombre());
        verify(categoryPersistencePort, times(1)).categoryExistsByName(category.getNombre());
    }

    @Test
    void testCreateCategoryWithDuplicateName() {
        when(categoryPersistencePort.categoryExistsByName(category.getNombre())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.createCategory(category));

        verify(categoryPersistencePort, times(1)).categoryExistsByName(category.getNombre());
    }

    @Test
    void testCreateCategoryWithLongName() {
        category.setNombre("Nombre de marca que excede los cincuenta caracteres permitidos");

        assertThrows(LimitCharactersException.class, () -> categoryUseCase.createCategory(category));

        verify(categoryPersistencePort, times(0)).saveCategory(category);
    }

    @Test
    void testCreateCategoryWithEmptyDescription() {
        category.setDescripcion("   ");

        assertThrows(EmptyOrNullException.class, () -> categoryUseCase.createCategory(category));

        verify(categoryPersistencePort, times(0)).saveCategory(category);
    }

    @Test
    void testCreateCategoryWithLongDescription() {
        category.setDescripcion("Esta es una descripción de prueba que es intencionalmente larga y supera los noventa caracteres permitidos en el sistema para probar la validación.");

        assertThrows(LimitCharactersException.class, () -> categoryUseCase.createCategory(category));

        verify(categoryPersistencePort, times(0)).saveCategory(category);
    }

    @Test
    void testListCategory() {
        List<Category> categories = Arrays.asList(category);

        when(categoryPersistencePort.getAllCategories(0, 10, "nombre", "asc")).thenReturn(categories);

        List<Category> result = categoryUseCase.listCategories(0, 10, "nombre", "asc");

        assertEquals(1, result.size());
        assertEquals(category.getNombre(), result.get(0).getNombre());
        verify(categoryPersistencePort, times(1)).getAllCategories(0, 10, "nombre", "asc");
    }
}
