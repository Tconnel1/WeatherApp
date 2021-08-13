
package com.example.springbootproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Weather {

    private String description;
    private String icon;
    private Long id;
    private String main;
}
