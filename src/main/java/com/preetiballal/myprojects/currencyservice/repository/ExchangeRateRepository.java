package com.preetiballal.myprojects.currencyservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.preetiballal.myprojects.currencyservice.model.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>{
    Optional<ExchangeRate> findByFromCurrencyAndToCurrency(String from, String to);
}
