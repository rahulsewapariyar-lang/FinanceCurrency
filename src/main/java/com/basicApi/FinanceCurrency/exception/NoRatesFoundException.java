package com.basicApi.FinanceCurrency.exception;

public class NoRatesFoundException extends RuntimeException{
    public NoRatesFoundException(String message) {
        super(message);
    }
}
