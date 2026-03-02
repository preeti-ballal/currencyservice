package com.preetiballal.myprojects.currencyservice.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class LiveAPIResponse {
    private String result;
    private Map<String, BigDecimal> conversion_rates;
}
