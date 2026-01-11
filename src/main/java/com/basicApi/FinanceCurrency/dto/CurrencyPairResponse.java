package com.basicApi.FinanceCurrency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyPairResponse {
    String baseCurrency;
    String targetCurrency;
}
