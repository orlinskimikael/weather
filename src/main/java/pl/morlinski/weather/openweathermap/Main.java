package pl.morlinski.weather.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private double temp;
    private String pressure;
    private String humidity;
    private String temp_min;
    private String temp_max;
    private String sea_level;
    private String grnd_level;
}
