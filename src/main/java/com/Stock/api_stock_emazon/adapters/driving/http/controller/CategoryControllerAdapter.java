package com.Stock.api_stock_emazon.adapters.driving.http.controller;

import com.Stock.api_stock_emazon.adapters.driving.http.dto.AddCategoryRequest;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.CategoryResponse;
import com.Stock.api_stock_emazon.adapters.driving.http.dto.ErrorResponse;
import com.Stock.api_stock_emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.Stock.api_stock_emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.Stock.api_stock_emazon.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
@RequiredArgsConstructor
public class CategoryControllerAdapter {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/crear")
    public ResponseEntity<ErrorResponse> addCategory(@RequestBody AddCategoryRequest request) {
        try {
            categoryServicePort.createCategory(categoryRequestMapper.toCategory(request));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

        }

    }

    @GetMapping("/listarCategorias")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size,@RequestParam String sortBy,
                                                                   @RequestParam String sortOrder) {
        return ResponseEntity.ok(
                categoryResponseMapper.toCategoryResponseList(
                        categoryServicePort.listCategories(page, size, sortBy, sortOrder))
        );
    }
}
