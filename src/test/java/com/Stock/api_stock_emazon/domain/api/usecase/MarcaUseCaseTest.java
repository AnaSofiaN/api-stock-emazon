package com.Stock.api_stock_emazon.domain.api.usecase;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.repository.IMarcaRepository;
import com.Stock.api_stock_emazon.domain.model.Marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;



@ExtendWith(MockitoExtension.class)
public class MarcaUseCaseTest {

    @Mock
    private IMarcaRepository marcaRepository;

    @InjectMocks
    private MarcaUseCase marcaUseCase;

    private Marca marca;

    @BeforeEach
    void setUp() {
        marca = new Marca();
        marca.setId(1L);
        marca.setNombre("Marca de Prueba");
        marca.setDescripcion("Descripción de prueba");
    }

    @Test
    void testCreateBrand() {
        when(marcaRepository.save(any(Marca.class))).thenReturn(marca);

        Marca createdBrand = marcaUseCase.createBrand(marca);

        assertNotNull(createdBrand);
        assertEquals(marca.getNombre(), createdBrand.getNombre());
        verify(marcaRepository, times(1)).save(marca);
    }

    @Test
    void testListBrands() {
        List<Marca> brands = Arrays.asList(marca);
        Page<Marca> page = new PageImpl<>(brands);
        Pageable pageable = PageRequest.of(0, 10);

        when(marcaRepository.findAll(pageable)).thenReturn(page);

        Page<Marca> result = marcaUseCase.getAllBrands(pageable);

        assertEquals(1, result.getTotalElements());
        verify(marcaRepository, times(1)).findAll(pageable);
    }
}
