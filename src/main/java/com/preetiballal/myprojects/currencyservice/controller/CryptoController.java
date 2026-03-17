package com.preetiballal.myprojects.currencyservice.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.preetiballal.myprojects.currencyservice.service.CryptoService;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    // Endpoint: GET http://localhost:8080/api/crypto/price?coin=bitcoin&currency=usd
    @GetMapping("/price")
    public ResponseEntity<BigDecimal> getPrice(
            @RequestParam String coin, 
            @RequestParam String currency) {
        try {
            BigDecimal price = cryptoService.getPrice(coin, currency);
            return ResponseEntity.ok(price);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
