package com.Stock.api_stock_emazon.adapters.driving.http.controller;

import com.Stock.api_stock_emazon.adapters.driven.jpa.mysql.mapper.IMarcaEntityMapper;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.*;
import com.Stock.api_stock_emazon.adapters.driving.http.mapper.IMarcaRequestMapper;
import com.Stock.api_stock_emazon.adapters.driving.http.mapper.IMarcaResponseMapper;
import com.Stock.api_stock_emazon.domain.api.IMarcaServicePort;
import com.Stock.api_stock_emazon.domain.spi.IMarcaPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marca")
@RequiredArgsConstructor
public class MarcaControllerAdapter {
    private final IMarcaServicePort marcaServicePort;
    private final IMarcaPersistencePort marcaPersistencePort;
    private final IMarcaEntityMapper marcaEntityMapper;
    private final IMarcaRequestMapper marcaRequestMapper;
    private final IMarcaResponseMapper marcaResponseMapper;

    @PostMapping("/crear")
    public ResponseEntity<ErrorResponse> addMarca(@RequestBody AddMarcaRequest request) {
        try {
            marcaServicePort.createBrand(marcaRequestMapper.toMarca(request));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }

    }

    @GetMapping("/listarMarcas")
    public ResponseEntity<List<MarcaResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy,
                                                                @RequestParam String sortOrder) {
        return ResponseEntity.ok(
                marcaResponseMapper.toMarcaResponseList(
                        marcaServicePort.getAllBrands(page, size, sortBy, sortOrder))
        );
    }

}
