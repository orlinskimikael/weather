package pl.morlinski.weather.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String id;
    private String main;
    private String description;
    private String icon;
}
