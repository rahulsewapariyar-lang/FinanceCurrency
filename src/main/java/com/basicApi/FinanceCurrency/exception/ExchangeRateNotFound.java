package com.basicApi.FinanceCurrency.exception;

public class ExchangeRateNotFound extends RuntimeException{
    public ExchangeRateNotFound(String message1,String message2) {
        super("Exchange rate not found for "+message1+" to "+message2+"");
    }
    public ExchangeRateNotFound(String message) {
        super(message);
    }
}
