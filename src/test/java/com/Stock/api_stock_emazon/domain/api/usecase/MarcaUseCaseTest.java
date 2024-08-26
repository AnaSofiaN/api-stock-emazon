package com.Stock.api_stock_emazon.domain.api.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.Stock.api_stock_emazon.domain.exception.CategoryAlreadyExistsException;
import com.Stock.api_stock_emazon.domain.exception.EmptyOrNullException;
import com.Stock.api_stock_emazon.domain.exception.LimitCharactersException;
import com.Stock.api_stock_emazon.domain.spi.IMarcaPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Stock.api_stock_emazon.domain.model.Marca;


import java.util.Arrays;
import java.util.List;



@ExtendWith(MockitoExtension.class)
class MarcaUseCaseTest {

    @Mock
    private IMarcaPersistencePort marcaPersistencePort;

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
        when(marcaPersistencePort.existsByName(anyString())).thenReturn(false);

        Marca createdBrand = marcaUseCase.createBrand(marca);

        assertNotNull(createdBrand);
        assertEquals(marca.getNombre(), createdBrand.getNombre());
        verify(marcaPersistencePort, times(1)).existsByName(marca.getNombre());
    }

    @Test
    void testCreateBrandWithDuplicateName() {
        when(marcaPersistencePort.existsByName(marca.getNombre())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> marcaUseCase.createBrand(marca));

        verify(marcaPersistencePort, times(1)).existsByName(marca.getNombre());
    }

    @Test
    void testCreateBrandWithLongName() {
        marca.setNombre("Nombre de marca que excede los cincuenta caracteres permitidos");

        assertThrows(LimitCharactersException.class, () -> marcaUseCase.createBrand(marca));

        verify(marcaPersistencePort, times(0)).saveBrand(marca);
    }
    @Test
    void testCreateBrandWithEmptyDescription() {
        marca.setDescripcion("   ");

        assertThrows(EmptyOrNullException.class, () -> marcaUseCase.createBrand(marca));

        verify(marcaPersistencePort, times(0)).saveBrand(marca);
    }


    @Test
    void testCreateBrandWithLongDescription() {
        marca.setDescripcion("Esta es una descripción de prueba que es intencionalmente larga y supera los ciento veinte caracteres permitidos en el sistema para probar la validación.");

        assertThrows(LimitCharactersException.class, () -> marcaUseCase.createBrand(marca));

        verify(marcaPersistencePort, times(0)).saveBrand(marca);
    }


    @Test
    void testListBrands() {
        List<Marca> brands = Arrays.asList(marca);

        when(marcaPersistencePort.getAllBrands(0, 10, "descripcion", "asc")).thenReturn(brands);

        List<Marca> result = marcaUseCase.getAllBrands(0, 10, "descripcion", "asc");

        assertEquals(1, result.size());
        assertEquals(marca.getNombre(), result.get(0).getNombre());
        verify(marcaPersistencePort, times(1)).getAllBrands(0, 10, "descripcion", "asc");
    }
}
