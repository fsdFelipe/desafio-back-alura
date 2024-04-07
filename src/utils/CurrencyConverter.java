package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyConverter {
    private static final String API_KEY = "5ac194049ee8693f3f3debe0";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/5ac194049ee8693f3f3debe0/latest/";

    private static final List<String> ALLOWED_CURRENCIES = Arrays.asList("ARS", "BOB", "BRL", "CLP", "COP", "USD");

    public static double convertCurrency(double amount, String fromCurrency, String toCurrency) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + fromCurrency))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

        if (!rates.has(toCurrency)) {
            throw new IllegalArgumentException("Moeda de destino inválida");
        }

        double exchangeRate = rates.get(toCurrency).getAsDouble();

        return amount * exchangeRate;
    }

    public static List<String> getAllowedCurrencies() {
        return ALLOWED_CURRENCIES;
    }

    public static List<String> getFilteredCurrencies(String baseCurrency) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + baseCurrency))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(responseBody).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

        List<String> filteredCurrencies = rates.keySet().stream()
                .filter(ALLOWED_CURRENCIES::contains)
                .collect(Collectors.toList());

        return filteredCurrencies;
    }
}
