package com.Stock.api_stock_emazon.domain.util;

public class ErrorConstants {
    private ErrorConstants() {

    }

    public static final String ALREADY_EXIST_CATEGORY = "El nombre de la categoría ya existe.";
    public static final String LIMIT_CHARACTERS_NAME_CATEGORY = "El nombre de la categoría no puede tener más de 50 caracteres.";
    public static final String LIMIT_CHARACTERS_DESCRIPTION_CATEGORY = "La descripción de la categoría no puede tener más de 90 caracteres.";
    public static final String EMPTY_DESCRIPTION = "La descripción no puede venir vacia.";

    public static final String ALREADY_EXIST_MARCA = "El nombre de la marca ya existe.";
    public static final String LIMIT_CHARACTERS_NAME_MARCA = "El nombre de la marca no puede tener más de 50 caracteres.";
    public static final String LIMIT_CHARACTERS_DESCRIPTION_MARCA = "La descripción de la marca no puede tener más de 120 caracteres.";

    public static final String LIMIT_CATEGORY_ARTICULO = "El artículo debe tener entre 1 y 3 categorías asociadas.";
    public static final String LIMIT_RELATIONAL_CATEGORY_ARTICULO = "El artículo no puede tener categorías duplicadas.";

}
