package com.preetiballal.myprojects.currencyservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.preetiballal.myprojects.currencyservice.repository.ExchangeRateRepository;

@Service
public class CurrencyService {
    
    private final ExchangeRateRepository exchangeRateRepository;

    public CurrencyService(ExchangeRateRepository exRepository){
        this.exchangeRateRepository = exRepository;
    }

    public BigDecimal convert(String from, String to, BigDecimal amount){
        //1. Find rate from db. If not found, throw error
        //2. Calculate and round to 2 decimal
        return exchangeRateRepository.findByFromCurrencyAndToCurrency(from,to)
            .map(rate -> amount.multiply(rate.getRate())
                        .setScale(2, RoundingMode.HALF_UP))
            .orElseThrow(() -> new RuntimeException("Exchange rate not found !"));
    }
}
