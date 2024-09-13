package com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.Stock.api_stock_emazon.domain.model.Category;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
        List<CategoryEntity> categories = categoryRepository.findAll(pageable).getContent();
        return categoryEntityMapper.toModelList(categories);
    }

    @Override
    public boolean categoryExistsByName(String name) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findByNombre(name);
        return categoryEntity.isPresent();
    }

    @Override
    public Category getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
        return categoryEntityMapper.toModel(categoryEntity);
    }
}
