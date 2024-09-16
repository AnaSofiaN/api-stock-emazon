package com.Stock.api_stock_emazon.adapters.driving.http.controller;

import com.Stock.api_stock_emazon.domain.api.usecase.SuministroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/suministros")
@RequiredArgsConstructor
public class SuministroController {

    private final SuministroUseCase suministroUseCase;

    @PostMapping("/agregar")
    public ResponseEntity<Void> agregarSuministro(
            @RequestParam Long articuloId,
            @RequestParam int cantidad,
            @RequestParam Long usuarioId) {

        suministroUseCase.agregarSuministro(articuloId, cantidad, usuarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
