package com.Stock.api_stock_emazon.configuration;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.Stock.api_stock_emazon.domain.api.ICategoryServicePort;
import com.Stock.api_stock_emazon.domain.api.usercase.CategoryUseCase;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
