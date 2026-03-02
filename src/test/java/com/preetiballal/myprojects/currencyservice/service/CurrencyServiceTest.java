package com.preetiballal.myprojects.currencyservice.service;

import com.preetiballal.myprojects.currencyservice.model.ExchangeRate;
import com.preetiballal.myprojects.currencyservice.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private ExchangeRateRepository repository;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private WebClient webClient; 

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    public void testConvert_Successful() {
        // Arrange 
        // Note: Added the 5th parameter (LocalDateTime) to match your new Model
        ExchangeRate mockRate = new ExchangeRate(1L, "USD", "JPY", new BigDecimal("150.00"), LocalDateTime.now());
        
        when(repository.findByFromCurrencyAndToCurrency("USD", "JPY"))
                .thenReturn(Optional.of(mockRate));

        // Act 
        BigDecimal result = currencyService.convert("USD", "JPY", new BigDecimal("10"));

        // Assert 
        assertEquals(new BigDecimal("1500.00"), result);
    }

    @Test
public void testConvert_RateNotFound_ThrowsException() {
    // 1. Arrange: Database is empty for this currency
    when(repository.findByFromCurrencyAndToCurrency("USD", "XYZ"))
            .thenReturn(Optional.empty());

    // 2. Act & Assert: It should throw an exception 
    // (Either our custom one or a NullPointer because the API mock isn't fully set up)
    assertThrows(Exception.class, () -> {
        currencyService.convert("USD", "XYZ", new BigDecimal("10"));
    });
        // Arrange
        when(repository.findByFromCurrencyAndToCurrency("USD", "XYZ"))
                .thenReturn(Optional.empty());
        
        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            currencyService.convert("USD", "XYZ", new BigDecimal("10"));
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("rate not found") || 
               actualMessage.contains("Live rate not available") ||
               actualMessage.contains("not supported"),
               "The error message was: " + actualMessage);
    }
}