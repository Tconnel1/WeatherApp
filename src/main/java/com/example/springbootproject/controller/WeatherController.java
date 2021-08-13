package com.example.springbootproject.controller;

import com.example.springbootproject.responses.WeatherResponse;
import com.example.springbootproject.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/onecall")
    public WeatherResponse weatherForecastAverage(@RequestParam String lat,
                                                  @RequestParam String lon) {
        return weatherService.returnWarmestDay(lat, lon);
    }
}
