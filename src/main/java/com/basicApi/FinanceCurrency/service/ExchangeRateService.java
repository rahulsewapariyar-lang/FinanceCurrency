package com.basicApi.FinanceCurrency.service;

import com.basicApi.FinanceCurrency.dto.CurrencyPairResponse;
import com.basicApi.FinanceCurrency.dto.ExchangeRateRequest;
import com.basicApi.FinanceCurrency.dto.ExchangeRateResponse;
import com.basicApi.FinanceCurrency.exception.ExchangeRateNotFound;
import com.basicApi.FinanceCurrency.exception.NoRatesFoundException;
import com.basicApi.FinanceCurrency.exception.CurrencyNotFoundException;
import com.basicApi.FinanceCurrency.exception.InvalidRequestException;
import com.basicApi.FinanceCurrency.mapper.ExchangeRateMapper;
import com.basicApi.FinanceCurrency.model.ExchangeRates;
import com.basicApi.FinanceCurrency.repository.ExchangeRateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ExchangeRateService {

    ExchangeRateRepository exchangeRateRepository;
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository){
        this.exchangeRateRepository = exchangeRateRepository;
    }
    public ResponseEntity<ExchangeRateResponse> getTargetCurrencyByName(String baseCurrency,String targetCurrency) {
        ExchangeRates er = exchangeRateRepository.findByBaseCurrencyAndTargetCurrency(baseCurrency,targetCurrency)
                .orElseThrow(()->new CurrencyNotFoundException("Desired Currency is not found!"));
        ExchangeRateResponse err = ExchangeRateMapper.INSTANCE.mapExchangeRateEntityToDto(er);
        return ResponseEntity.ok(err);
    }

    public ExchangeRateResponse getByID(Long id) {
        ExchangeRates er = exchangeRateRepository.findById(id)
                .orElseThrow(()->new CurrencyNotFoundException("Currency not found by id exception"));
                return ExchangeRateMapper.INSTANCE.mapExchangeRateEntityToDto(er);
    }

    public ExchangeRateResponse addExchangeRate(ExchangeRateRequest exchangeRateRequest) {

        if(exchangeRateRequest.getBaseCurrency()==null || exchangeRateRequest.getTargetCurrency()==null){
            throw new InvalidRequestException("Base currency or target Currency cannot be null");
        }
        exchangeRateRepository.findByBaseCurrencyAndTargetCurrency(exchangeRateRequest.getBaseCurrency(),exchangeRateRequest.getTargetCurrency())
                        .ifPresent(existing->{
                            throw new InvalidRequestException("Exchange rate already exists");
                        });
         ExchangeRates res =  ExchangeRateMapper.INSTANCE.mapDtoToExchangeRateEntity(exchangeRateRequest);
         res.setLocalDateTime(java.time.LocalDateTime.now());

         exchangeRateRepository.save(res);
        return ExchangeRateMapper.INSTANCE.mapExchangeRateEntityToDto(res);
    }

    public List<ExchangeRateResponse> findAll() {
        List<ExchangeRates> exchangeRates = exchangeRateRepository.findAll();
        if(exchangeRates.isEmpty()){
             throw new NoRatesFoundException("No exchange rates found");
         }
         return exchangeRates.stream().map(ExchangeRateMapper.INSTANCE::mapExchangeRateEntityToDto).toList();
    }

    public ExchangeRateResponse updateRate(Long id, ExchangeRateRequest exchangeRateRequest){


        ExchangeRates rate = exchangeRateRepository.findById(id)
                .orElseThrow(()->new CurrencyNotFoundException("Currency not found by id exception"));
        if(Objects.equals(exchangeRateRequest.getExchangeRate(),rate.getExchangeRate())){
            throw new InvalidRequestException("Exchange rate cannot be the same");

        }
        Double newRate = exchangeRateRequest.getExchangeRate();
        Double oldRate = rate.getExchangeRate();
        if (newRate == null) {
            throw new InvalidRequestException("Exchange rate cannot be the same");
        }
        // Update the entity fields from DTO using the mapper
        ExchangeRateMapper.INSTANCE.updateEntityFromRequest(exchangeRateRequest, rate);
        // Update timestamp
        rate.setLocalDateTime(LocalDateTime.now());

        // Save the updated entity
        exchangeRateRepository.save(rate);
        return ExchangeRateMapper.INSTANCE.mapExchangeRateEntityToDto(rate);
    }
    public void deleteExchangeRate(Long id){
        exchangeRateRepository.findById(id)
                .orElseThrow(()-> new CurrencyNotFoundException("Currency not found by id exception"));
    }
   //check the exchangerate exists
    public ExchangeRateResponse getExchangeRates(String fromCurrency,String toCurrency) {
        String from = fromCurrency.toUpperCase();
        String to = toCurrency.toUpperCase();

        ExchangeRates exchangeRate = exchangeRateRepository.findByBaseCurrencyAndTargetCurrency(from,to)
                .orElseThrow(()->new ExchangeRateNotFound(from,to));
        return ExchangeRateMapper.INSTANCE.mapExchangeRateEntityToDto(exchangeRate);
    }
}
