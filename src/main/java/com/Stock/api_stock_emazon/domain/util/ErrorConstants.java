package com.Stock.api_stock_emazon.domain.util;

public class ErrorConstants {
    private ErrorConstants() {

    }

    public static final String ALREADY_EXIST_CATEGORY = "El nombre de la categoría ya existe.";
    public static final String LIMIT_CHARACTERS_NAME_CATEGORY = "El nombre de la categoría no puede tener más de 50 caracteres.";
    public static final String LIMIT_CHARACTERS_DESCRIPTION_CATEGORY = "La descripción de la categoría no puede tener más de 90 caracteres.";
    public static final String EMPTY_CATEGORY = "La descripción no puede venir vacia.";
}
