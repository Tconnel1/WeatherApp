
package com.example.springbootproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FeelsLike {

    private Double day;
    private Double eve;
    private Double morn;
    private Double night;
}
