package com.basicApi.FinanceCurrency.exception;


public class CurrencyNotFoundException extends RuntimeException{

    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
