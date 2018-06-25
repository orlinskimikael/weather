package pl.morlinski.weather.openweathermap;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rain {
    @JsonAlias("3h")
    private double rain3h;
}
