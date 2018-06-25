package pl.morlinski.weather.openweathermap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
    private String type;
    private String id;
    private String message;
    private String country;
    private String sunrise;
    private String sunset;
}
