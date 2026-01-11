package com.basicApi.FinanceCurrency.repository;

import com.basicApi.FinanceCurrency.dto.CurrencyPairResponse;
import com.basicApi.FinanceCurrency.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, Long> {

    /**
     * Fetch an ExchangeRate by base and target currency
     *
     * @param baseCurrency e.g., "USD"
     * @param targetCurrency e.g., "EUR"
     * @return Optional containing ExchangeRate if exists
     */
    Optional<ExchangeRates> findByBaseCurrencyAndTargetCurrency(
            String baseCurrency,
            String targetCurrency
    );
}
