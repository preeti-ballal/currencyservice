package com.preetiballal.myprojects.currencyservice.service;

import com.preetiballal.myprojects.currencyservice.model.ExchangeRate;
import com.preetiballal.myprojects.currencyservice.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private ExchangeRateRepository repository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    public void testConvert_Successful() {
        // Arrange 
        ExchangeRate mockRate = new ExchangeRate(1L, "USD", "JPY", new BigDecimal("150.00"));
        when(repository.findByFromCurrencyAndToCurrency("USD", "JPY"))
                .thenReturn(Optional.of(mockRate));

        // Act 
        BigDecimal result = currencyService.convert("USD", "JPY", new BigDecimal("10"));

        // Assert 
        assertEquals(new BigDecimal("1500.00"), result);
    }

    @Test
public void testConvert_RateNotFound_ThrowsException() {
    // Arrange: Tell the mock to return "Empty" when looking for XYZ
    when(repository.findByFromCurrencyAndToCurrency("USD", "XYZ"))
            .thenReturn(Optional.empty());

    // Act & Assert: Check that a RuntimeException is thrown
    Exception exception = assertThrows(RuntimeException.class, () -> {
        currencyService.convert("USD", "XYZ", new BigDecimal("10"));
    });

    // Verify the error message matches what we wrote in the Service
    String expectedMessage = "Exchange rate not found";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
}
}
