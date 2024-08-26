package com.Stock.api_stock_emazon.domain.api.usecase;

import com.Stock.api_stock_emazon.domain.api.IMarcaServicePort;
import com.Stock.api_stock_emazon.domain.exception.CategoryAlreadyExistsException;
import com.Stock.api_stock_emazon.domain.exception.EmptyOrNullException;
import com.Stock.api_stock_emazon.domain.exception.LimitCharactersException;
import com.Stock.api_stock_emazon.domain.model.Marca;
import com.Stock.api_stock_emazon.domain.spi.IMarcaPersistencePort;
import com.Stock.api_stock_emazon.domain.util.ErrorConstants;
import com.Stock.api_stock_emazon.domain.util.LimitNumberConstants;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MarcaUseCase implements IMarcaServicePort {

    private final IMarcaPersistencePort marcaPersistencePort;

    @Override
    public Marca createBrand(Marca marca) {
        if (marcaPersistencePort.existsByName(marca.getNombre())) {
            throw new CategoryAlreadyExistsException(ErrorConstants.ALREADY_EXIST_MARCA);
        }
        if (marca.getNombre().length() > LimitNumberConstants.LIMIT_50) {
            throw new LimitCharactersException(ErrorConstants.LIMIT_CHARACTERS_NAME_MARCA);
        }
        if(marca.getDescripcion().isBlank() || marca.getNombre().isEmpty()) {
            throw new EmptyOrNullException(ErrorConstants.EMPTY_DESCRIPTION);
        }
        if (marca.getDescripcion().length() > LimitNumberConstants.LIMIT_120) {
            throw new LimitCharactersException(ErrorConstants.LIMIT_CHARACTERS_DESCRIPTION_MARCA);
        }
        marcaPersistencePort.saveBrand(marca);
        return  marca;
    }

    @Override
    public List<Marca> getAllBrands(int page, int size, String sortBy, String sortDirection) {
        return marcaPersistencePort.getAllBrands(page, size, sortBy, sortDirection);
    }
}
