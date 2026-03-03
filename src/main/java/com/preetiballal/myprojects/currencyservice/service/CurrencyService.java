package com.preetiballal.myprojects.currencyservice.service;

import com.preetiballal.myprojects.currencyservice.dto.LiveAPIResponse;
import com.preetiballal.myprojects.currencyservice.model.ExchangeRate;
import com.preetiballal.myprojects.currencyservice.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class CurrencyService {
    
    private final ExchangeRateRepository exRepository;

    private final WebClient webClient;

    @Value("${api.exchangerate.key}")
    private String apiKey;

    @Value("${api.exchangerate.url}")
    private String apiUrl;

    public CurrencyService(ExchangeRateRepository exRepository, WebClient webClient){
        this.exRepository = exRepository;
        this.webClient = webClient;
    }

    public BigDecimal convert(String from, String to, BigDecimal amount){
        //1. Find rate from db. If not found, find in live API
        //2. Calculate and round to 2 decimal
        BigDecimal rate = exRepository.findByFromCurrencyAndToCurrency(from, to)
                .map(ExchangeRate::getRate)
                .orElseGet(() -> fetchLiveRate(from, to));

        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal fetchLiveRate(String from, String to) {
        String url = apiUrl + apiKey + "/latest/" + from;
        
        LiveAPIResponse response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(LiveAPIResponse.class)
                .block(); // .block() is used here for simplicity in this project

        if (response != null && response.getConversion_rates().containsKey(to)) {
            BigDecimal liveRate = response.getConversion_rates().get(to);
            
            // Save to DB so we don't have to call the API again for this pair
            exRepository.save(new ExchangeRate(null, from, to, liveRate,LocalDateTime.now()));
            return liveRate;
        }

        throw new RuntimeException("Live rate not available for " + from + " to " + to);
    }
}
