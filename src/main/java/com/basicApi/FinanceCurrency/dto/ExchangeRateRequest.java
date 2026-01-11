package com.basicApi.FinanceCurrency.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExchangeRateRequest {

    @NotNull
    @Size(min = 3, max = 3)
    private String baseCurrency;
    @Size(min = 3, max = 3)
    private String targetCurrency;

    @NotNull
    @PositiveOrZero(message = "Cannot be negative")
    private Double exchangeRate;
}
