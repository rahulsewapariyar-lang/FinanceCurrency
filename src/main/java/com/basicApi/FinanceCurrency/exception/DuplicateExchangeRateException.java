package com.basicApi.FinanceCurrency.exception;

public class DuplicateExchangeRateException extends RuntimeException{
    public DuplicateExchangeRateException(String message) {
        super(message);
    }
}
