package com.SpringBoot.JournalApp.Service;

import com.SpringBoot.JournalApp.Constants.Placeholders;
import com.SpringBoot.JournalApp.api.response.WeatherResponse;
import com.SpringBoot.JournalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        try {
            // Add null checks and logging
            String weatherApiTemplate = AppCache.APP_Cache.get("weather_api");
            if (weatherApiTemplate == null) {
                System.err.println("Weather API template not found in cache");
                return null;
            }

            if (apiKey == null || apiKey.isEmpty()) {
                System.err.println("Weather API key is not configured");
                return null;
            }

            String finalAPI = weatherApiTemplate.replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            System.out.println("Making weather API call to: " + finalAPI.replace(apiKey, "***API_KEY***"));

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if (body != null && body.getCurrent() != null) {
                System.out.println("Weather data retrieved successfully for " + city);
                System.out.println("Temperature: " + body.getCurrent().getTemperature() + "°C");
                System.out.println("Feels like: " + body.getCurrent().getFeelslike() + "°C");
            } else {
                System.err.println("Weather response body is null or current data is missing");
            }

            return body;
        } catch (Exception e) {
            System.err.println("Error fetching weather data for city: " + city);
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}