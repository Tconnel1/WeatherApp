package com.example.springbootproject.responses;

import com.example.springbootproject.models.Daily;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class WeatherResponse {

    private Daily maxTemp;
}
