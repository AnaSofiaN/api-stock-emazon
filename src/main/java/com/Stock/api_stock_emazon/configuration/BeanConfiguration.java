package com.Stock.api_stock_emazon.configuration;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter.ArticuloAdapter;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter.MarcaAdapter;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.adapter.SuministroAdapter;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IArticuloEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IMarcaEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.ISuministroEntityMapper;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IArticuloRepository;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IHistorialSuministroRepository;
import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IMarcaRepository;
import com.Stock.api_stock_emazon.domain.api.IArticuloServicePort;
import com.Stock.api_stock_emazon.domain.api.ICategoryServicePort;
import com.Stock.api_stock_emazon.domain.api.IMarcaServicePort;
import com.Stock.api_stock_emazon.domain.api.ISuministroServicePort;
import com.Stock.api_stock_emazon.domain.api.usecase.ArticuloUseCase;
import com.Stock.api_stock_emazon.domain.api.usecase.CategoryUseCase;
import com.Stock.api_stock_emazon.domain.api.usecase.MarcaUseCase;
import com.Stock.api_stock_emazon.domain.api.usecase.SuministroUseCase;
import com.Stock.api_stock_emazon.domain.spi.IArticuloPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.ICategoryPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.IHistorialSuministroPersistencePort;
import com.Stock.api_stock_emazon.domain.spi.IMarcaPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IMarcaRepository marcaRepository;
    private final IMarcaEntityMapper marcaEntityMapper;
    private final IArticuloRepository articuloRepository;
    private final IArticuloEntityMapper articuloEntityMapper;
    private final IHistorialSuministroRepository suministroRepository;
    private final ISuministroEntityMapper suministroEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IMarcaPersistencePort marcaPersistencePort() {
        return new MarcaAdapter(marcaRepository, marcaEntityMapper);
    }

    @Bean
    public IMarcaServicePort marcaServicePort() {
        return new MarcaUseCase(marcaPersistencePort());
    }

    @Bean
    public IArticuloPersistencePort articuloPersistencePort() {
        return new ArticuloAdapter(articuloRepository, articuloEntityMapper);
    }

    @Bean
    public IArticuloServicePort articuloServicePort() {
        return new ArticuloUseCase(articuloPersistencePort(), categoryPersistencePort(), categoryEntityMapper,articuloRepository,articuloEntityMapper,categoryRepository);
    }

    @Bean
    public IHistorialSuministroPersistencePort suministroPersistencePort() {
        return new SuministroAdapter(suministroRepository, suministroEntityMapper);
    }

    @Bean
    public ISuministroServicePort suministroServicePort() {
        return new SuministroUseCase(articuloPersistencePort(), suministroPersistencePort());
    }


}
