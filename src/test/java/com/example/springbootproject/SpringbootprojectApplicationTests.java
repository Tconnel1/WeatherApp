package com.example.springbootproject;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springbootproject.responses.WeatherResponse;
import com.example.springbootproject.services.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class SpringbootprojectApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    private WeatherService weatherService;

    @BeforeEach
    public void setUP() {
        weatherService = new WeatherService(restTemplate);
    }

    @Test
    void getWarmestTemperature_weatherForecastFound_displayTemperature() {
        WeatherResponse result = weatherService.returnWarmestDay("33.44", "-94.04");

        assertThat(result).isNotNull();
    }
}
