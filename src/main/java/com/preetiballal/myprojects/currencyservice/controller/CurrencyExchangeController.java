package com.preetiballal.myprojects.currencyservice.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.preetiballal.myprojects.currencyservice.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/currency")
public class CurrencyExchangeController {
    
    public final CurrencyService currencyService;

    public CurrencyExchangeController(CurrencyService service){
        this.currencyService = service;   
    }

    @GetMapping("/convert")
    
    public BigDecimal convert(
        @RequestParam String from,
        @RequestParam String to,
        @RequestParam BigDecimal amount){
            return currencyService.convert(from, to, amount);
    }

}
