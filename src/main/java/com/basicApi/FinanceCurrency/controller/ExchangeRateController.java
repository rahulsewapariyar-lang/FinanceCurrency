package com.basicApi.FinanceCurrency.controller;

import com.basicApi.FinanceCurrency.dto.CurrencyPairResponse;
import com.basicApi.FinanceCurrency.dto.ExchangeRateRequest;
import com.basicApi.FinanceCurrency.dto.ExchangeRateResponse;
import com.basicApi.FinanceCurrency.repository.ExchangeRateRepository;
import com.basicApi.FinanceCurrency.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExchangeRateController {

     private final ExchangeRateService exchangeRateService;
    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateController(ExchangeRateService exchangeRateService, ExchangeRateRepository exchangeRateRepository){
         this.exchangeRateService = exchangeRateService;
        this.exchangeRateRepository = exchangeRateRepository;
    }
      @GetMapping("/exchange-rate/targetbyname")
      public ResponseEntity<ExchangeRateResponse> getExchangeRate(@RequestParam String baseCurrency,
                                                                  @RequestParam String targetCurrency){
           return exchangeRateService.getTargetCurrencyByName(baseCurrency,targetCurrency);
      }
      @GetMapping("/exchange-rate/{id}")
          public ResponseEntity<ExchangeRateResponse> getExchangeRateById(@PathVariable Long id){
           ExchangeRateResponse response = exchangeRateService.getByID(id);
           return ResponseEntity.ok(response);
      }
//      @GetMapping("/exchange-rate/available-pairs")
//          public ResponseEntity<List<CurrencyPairResponse>> getCurrencyPair(){
//           List<CurrencyPairResponse> response = exchangeRateService.getCurrencyPair();
//           return ResponseEntity.ok(response);
//      }
      @PostMapping("/exchange-rate/add-rate")
            public ResponseEntity<ExchangeRateResponse> addExchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest){
            ExchangeRateResponse res = exchangeRateService.addExchangeRate(exchangeRateRequest);
            return ResponseEntity.ok(res);
      }
      @GetMapping("/exchange-rate/rates")
        public ResponseEntity<List<ExchangeRateResponse>> getAllExchangeRates(){
        List<ExchangeRateResponse>  exchangeRateResponses = exchangeRateService.findAll();
        return ResponseEntity.ok(exchangeRateResponses);
      }
      @PatchMapping("/exchange-rate/rate/{id}")
      public ResponseEntity<ExchangeRateResponse> updateExchangeRate(@PathVariable Long id, @Validated @RequestBody ExchangeRateRequest exchangeRateRequest){
        ExchangeRateResponse rate = exchangeRateService.updateRate(id,exchangeRateRequest);
        return ResponseEntity.ok(rate);
      }
      @DeleteMapping("/exchange-rate/delete/{id}")
      public ResponseEntity<ExchangeRateResponse> deleteFromDatabase(@PathVariable Long id){
         exchangeRateRepository.deleteById(id);
         return ResponseEntity.noContent().build();
      }
      //Get Request for project B
      // ✅ FIXED: Endpoint for Project B - check if currency pair exists
      @GetMapping("/from/{from}/to/{to}")
      public ResponseEntity<ExchangeRateResponse> getExchangeRates(
              @PathVariable String from,
              @PathVariable String to) {
          ExchangeRateResponse response = exchangeRateService.getExchangeRates(from, to);
          return ResponseEntity.ok(response);
      }
}
