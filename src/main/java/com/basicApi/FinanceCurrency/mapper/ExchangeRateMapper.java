package com.basicApi.FinanceCurrency.mapper;

import com.basicApi.FinanceCurrency.dto.ExchangeRateRequest;
import com.basicApi.FinanceCurrency.dto.ExchangeRateResponse;
import com.basicApi.FinanceCurrency.model.ExchangeRates;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExchangeRateMapper {

    ExchangeRateMapper INSTANCE = Mappers.getMapper( ExchangeRateMapper.class);
    //RequestDto ->Entity
    ExchangeRates mapDtoToExchangeRateEntity(ExchangeRateRequest exchangeRateRequest);
     //Entity->ResponseDto
    ExchangeRateResponse mapExchangeRateEntityToDto(ExchangeRates exchangeRates);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ExchangeRateRequest request, @MappingTarget ExchangeRates entity);
}
