package com.preetiballal.myprojects.currencyservice.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preetiballal.myprojects.currencyservice.service.CurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/currency")
@Tag(name = "Currency", description = "Endpoints for currency conversion")
public class CurrencyExchangeController {
    
    public final CurrencyService currencyService;

    public CurrencyExchangeController(CurrencyService service){
        this.currencyService = service;   
    }

    @GetMapping("/convert")
    @Operation(summary = "Convert currency", description = "Returns the converted amount based on live rates")
    public BigDecimal convert(
        @RequestParam String from,
        @RequestParam String to,
        @RequestParam BigDecimal amount){
            return currencyService.convert(from, to, amount);
    }

}
