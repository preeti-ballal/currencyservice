package com.preetiballal.myprojects.currencyservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.preetiballal.myprojects.currencyservice.repository.ExchangeRateRepository;

@Service
public class CurrencyService {
    
    private final ExchangeRateRepository exchangeRateRepository;

    public CurrencyService(ExchangeRateRepository exRepository){
        this.exchangeRateRepository = exRepository;
    }

    public BigDecimal convert(String from, String to, BigDecimal amount){
        return null;
    }
}
