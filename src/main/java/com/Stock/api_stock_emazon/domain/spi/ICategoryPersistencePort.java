package com.Stock.api_stock_emazon.domain.spi;

import com.Stock.api_stock_emazon.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    List<Category> getAllCategories(Integer page, Integer size, String sortBy, String sortDirection);
    boolean categoryExistsByName(String name);
    Category getCategoryById(Long id);
}
