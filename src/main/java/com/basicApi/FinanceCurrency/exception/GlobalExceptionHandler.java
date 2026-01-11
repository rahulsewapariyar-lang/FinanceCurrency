package com.basicApi.FinanceCurrency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> currencyNotFoundException(CurrencyNotFoundException cex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .message(cex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> invalidRequestException(InvalidRequestException cex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .message(cex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NoRatesFoundException.class)
    public ResponseEntity<ErrorResponse> invalidRequestException(NoRatesFoundException cex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .message(cex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ExchangeRateNotFound.class)
    public ResponseEntity<ErrorResponse> exchangeRatesNotFound(ExchangeRateNotFound cex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(java.time.LocalDateTime.now())
                .status(404)
                .message(cex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
