
package com.example.springbootproject.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class WeeklyForecast {

    private List<Daily> daily;
    private Double lat;
    private Double lon;
    private String timezone;
    private Long timezoneOffset;
}
