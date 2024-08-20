package com.Stock.api_stock_emazon.domain.exception;

public class LimitCharactersException extends RuntimeException{
    public LimitCharactersException(String message) { super(message); }
}
