package com.Stock.api_stock_emazon.adapters.driving.http.controller;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddArticuloRequest;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddCategoryRequest;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.ArticuloResponse;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.ErrorResponse;
import com.Stock.api_stock_emazon.adapters.driving.http.mapper.IArticuloRequestMapper;
import com.Stock.api_stock_emazon.adapters.driving.http.mapper.IArticuloResponseMapper;
import com.Stock.api_stock_emazon.domain.api.IArticuloServicePort;
import com.Stock.api_stock_emazon.domain.model.Articulo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articulo")
@RequiredArgsConstructor
public class ArticuloControllerAdapter {
    private final IArticuloServicePort articuloServicePort;
    private final IArticuloResponseMapper articuloResponseMapper;
    private final IArticuloRequestMapper articuloRequestMapper;

    @GetMapping("/listarArticulos")
    public ResponseEntity<List<ArticuloResponse>> listarArticulos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nombre") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<Articulo> articulos = articuloServicePort.listarArticulos(page, size, sortField, sortDirection);

        // Convertir a DTOs de respuesta
        List<ArticuloResponse> response = articuloResponseMapper.toPageArticuloResponse(articulos);

        return ResponseEntity.ok(
                articuloResponseMapper.toPageArticuloResponse(
                        articuloServicePort.listarArticulos(page, size, sortField, sortDirection))
        );
    }

    @PostMapping("/crear")
    public ResponseEntity<ErrorResponse> addCategory(@RequestBody AddArticuloRequest request) {
        try {
            articuloServicePort.createArticulo(articuloRequestMapper.toArticulo(request));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }

    }
}
