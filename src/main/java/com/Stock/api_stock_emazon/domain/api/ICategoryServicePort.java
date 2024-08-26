package com.Stock.api_stock_emazon.domain.api;

import com.Stock.api_stock_emazon.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    Category createCategory(Category category);
    List<Category> listCategories(Integer page, Integer size,String sortBy, String sortDirection);
}
