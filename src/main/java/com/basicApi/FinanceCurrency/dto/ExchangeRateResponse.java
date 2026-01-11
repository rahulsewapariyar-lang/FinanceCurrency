package com.basicApi.FinanceCurrency.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeRateResponse {

    private Double exchangeRate;
    private String baseCurrency;
    private String targetCurrency;
    private LocalDateTime localDateTime;
}
