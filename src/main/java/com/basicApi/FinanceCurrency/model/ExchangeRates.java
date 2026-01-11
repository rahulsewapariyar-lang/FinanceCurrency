package com.basicApi.FinanceCurrency.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="exchange_rates")
public class ExchangeRates {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Column(length = 3,nullable = false)
    String baseCurrency;
    @Column(length = 3,nullable = false)
    String targetCurrency;

    @Column(nullable = false)
    Double exchangeRate;

    @Column(nullable = false)
    LocalDateTime localDateTime;

    @PrePersist
    protected void onCreate() {
        if (localDateTime == null) {
            localDateTime = LocalDateTime.now();
        }
    }
}
