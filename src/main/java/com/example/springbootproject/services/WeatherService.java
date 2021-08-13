package com.example.springbootproject.services;

import com.example.springbootproject.models.Daily;
import com.example.springbootproject.models.Temp;
import com.example.springbootproject.models.WeeklyForecast;
import com.example.springbootproject.responses.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherService {
    private final String URI = "https://api.openweathermap.org/data/2.5/onecall";
    private final String API_ID = "3f159511ac64f5393feaa6f3c700c74b";

    private final RestTemplate restTemplate;

    /**
     * returns a response with the hottest day of the week. If multiple days share the same temperature, it will return the most humid day
     * @param lat is the latitude of the location being recorded
     * @param lon is the longitude of the lacation being recorded
     * @return a weatherResponse containing the warmest day
     */
    public WeatherResponse returnWarmestDay(String lat, String lon) {
        WeatherResponse weatherResponse = new WeatherResponse();
        try {
            WeeklyForecast weeklyForecast = restTemplate.getForObject(urlBuilder(lat, lon), WeeklyForecast.class);

            if (weeklyForecast != null) {
                List<Daily> warmestDays = getWarmestDays(weeklyForecast);

                if (warmestDays.size() > 1) {
                    getMostHumidDay(weatherResponse, warmestDays);
                } else {
                    weatherResponse.setMaxTemp(warmestDays.get(0));
                }
            }
        } catch (Exception e) {
            log.error("Error found looking for warmest temperature",e);
        }
        return weatherResponse;
    }

    /**
     * gets a list of the warmest days of the upcoming 7 days
     * @param weeklyForecast entire weather forecast for the upcoming week week
     * @return a list of of the warmest days
     */
    private List<Daily> getWarmestDays(WeeklyForecast weeklyForecast) {
        List<Daily> warmestDays = Collections.emptyList();
        Optional<Double> maxTemperature = weeklyForecast.getDaily()
                .stream()
                .map(Daily::getTemp)
                .map(Temp::getMax)
                .max(Comparator.comparing(Double::doubleValue));

        if (maxTemperature.isPresent()) {
            warmestDays = weeklyForecast.getDaily()
                                        .stream()
                                        .filter(daily -> daily.getTemp().getMax().equals(maxTemperature.get()))
                                        .collect(Collectors.toList());
        }
        return warmestDays;
    }

    /**
     * finds the most humid day in a list of hottest days
     * @param weatherResponse response that is returned to the user containing the warmest day
     * @param warmestDays list of warmest days
     */
    private void getMostHumidDay(WeatherResponse weatherResponse, List<Daily> warmestDays) {
        warmestDays.stream()
                .filter(daily -> daily.getHumidity() <= warmestDays.stream()
                        .map(Daily::getHumidity)
                        .max(Comparator.comparing(Long::longValue))
                        .get()).findAny().ifPresent(weatherResponse::setMaxTemp);
    }

    private String urlBuilder(String latitude, String longitude) {
        return String.format(URI.concat("?lat=%s")
                                .concat("&lon=%s")
                                .concat("&exclude=current,minutely,hourly,alerts")
                                .concat("&units=imperial")
                                .concat("&appId=%s"), latitude, longitude, API_ID);
    }
}

//https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=current,minutely,hourly,alerts&appid=3f159511ac64f5393feaa6f3c700c74b
//http://localhost:8080/weather/onecall?lat=33.44&lon=-94.04
