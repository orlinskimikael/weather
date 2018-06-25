package pl.morlinski.weather.openweathermap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private double temp;
    private String pressure;
    private String humidity;
    @JsonAlias("temp_min")
    private String tempMin;
    @JsonAlias("temp_max")
    private String tempMax;
    @JsonAlias("sea_level")
    private String seaLevel;
    @JsonAlias("grnd_level")
    private String grndLevel;
}
