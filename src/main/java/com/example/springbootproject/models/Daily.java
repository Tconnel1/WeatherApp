
package com.example.springbootproject.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Daily {

    private Long clouds;
    private Double dewPoint;
    private Long dt;
    private FeelsLike feelsLike;
    private Long humidity;
    private Double moonPhase;
    private Long moonrise;
    private Long moonset;
    private Double pop;
    private Long pressure;
    private Double rain;
    private Long sunrise;
    private Long sunset;
    private Temp temp;
    private Double uvi;
    private List<Weather> weather;
    private Long windDeg;
    private Double windGust;
    private Double windSpeed;
}
