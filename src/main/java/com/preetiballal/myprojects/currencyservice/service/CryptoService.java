package com.preetiballal.myprojects.currencyservice.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CryptoService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public BigDecimal getPrice(String coinId, String vsCurrency) throws Exception {
        String url = String.format(
            "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=%s",
            coinId, vsCurrency
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JsonNode root = mapper.readTree(response.body());
        return root.path(coinId).path(vsCurrency).decimalValue();
    }
}