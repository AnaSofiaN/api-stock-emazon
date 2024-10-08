package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.domain.api.ICategoryServicePort;
import com.Stock.api_stock_emazon.domain.exception.CategoryAlreadyExistsException;
import com.Stock.api_stock_emazon.domain.exception.EmptyOrNullException;
import com.Stock.api_stock_emazon.domain.exception.LimitCharactersException;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import com.Stock.api_stock_emazon.domain.util.ErrorConstants;
import com.Stock.api_stock_emazon.domain.util.LimitNumberConstants;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public Category createCategory(Category category) {
        // Lógica de negocio: Validación de reglas
        if (category.getNombre().length() > LimitNumberConstants.LIMIT_50) {
            throw new LimitCharactersException(ErrorConstants.LIMIT_CHARACTERS_NAME_CATEGORY);
        }
        if(category.getDescripcion().isBlank() || category.getNombre().isEmpty()) {
            throw new EmptyOrNullException(ErrorConstants.EMPTY_DESCRIPTION);
        }
        if (category.getDescripcion().length() > LimitNumberConstants.LIMIT_90) {
            throw new LimitCharactersException(ErrorConstants.LIMIT_CHARACTERS_DESCRIPTION_CATEGORY);
        }
        if (categoryPersistencePort.categoryExistsByName(category.getNombre())) {
            throw new CategoryAlreadyExistsException(ErrorConstants.ALREADY_EXIST_CATEGORY);
        }
        categoryPersistencePort.saveCategory(category);
        return category;
    }

    @Override
    public List<Category> listCategories(Integer page, Integer size, String sortBy, String sortDirection) {
        return categoryPersistencePort.getAllCategories(page, size, sortBy, sortDirection);
    }
}
