package pl.morlinski.weather.openweathermap;

import static pl.morlinski.weather.DateUtils.convertTimestampToLocalDataTime;
import static pl.morlinski.weather.DateUtils.timeIsBetween;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.morlinski.weather.Weather;

/**
 * Odpowiedz dla zapytania o obecną pogodę.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class CurrentWeatherResponse implements Weather {
    private Coord coord;
    private pl.morlinski.weather.openweathermap.Weather[] weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private long dt;
    private Sys sys;
    private String id;
    private String name;
    private String cod;

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.morlinski.weather.openweathermap.Weather#rain(java.time.LocalDateTime,
     * java.time.LocalDateTime)
     */
    @Override
    public double rain(LocalDateTime begin, LocalDateTime end) {
        LocalDateTime time = convertTimestampToLocalDataTime(dt);

        double rain3h = -1.0;
        if (timeIsBetween(time, begin, end)) {
            if(rain == null) {
                rain3h = 0.0;
            } else {
                rain3h = rain.getRain3h();
            }
        }

        log.info("RainCurrent: {}", rain3h);
        return rain3h;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.morlinski.weather.openweathermap.Weather#cloud(java.time.
     * LocalDateTime, java.time.LocalDateTime)
     */
    @Override
    public double cloud(LocalDateTime begin, LocalDateTime end) {
        LocalDateTime time = convertTimestampToLocalDataTime(dt);

        double cloud = -1.0;
        if (timeIsBetween(time, begin, end)) {
            if(clouds == null) {
                cloud = 0.0;
            } else {
                cloud = clouds.getAll();
            }
        }

        log.info("CloudCurrent: {}%", cloud);
        return cloud;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.morlinski.weather.openweathermap.Weather#temperature(java.time.
     * LocalDateTime, java.time.LocalDateTime)
     */
    @Override
    public double temperature(LocalDateTime begin, LocalDateTime end) {
        LocalDateTime time = convertTimestampToLocalDataTime(dt);

        double temperature = -1.0;
        if (timeIsBetween(time, begin, end)) {
            if(main == null) {
                temperature = 0.0;
            } else {
                temperature = main.getTemp();
            }
        }

        log.info("TemperatureCurrent: {}", temperature);
        return temperature;
    }
}
