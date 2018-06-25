package pl.morlinski.weather.openweathermap;

import static pl.morlinski.weather.DataConverter.convertTimestampToLocalDataTime;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import pl.morlinski.weather.Weather;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponse implements Weather {
    private static final Logger logger = LoggerFactory.getLogger(CurrentWeatherResponse.class);

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
        if (time.isAfter(begin) && time.isBefore(end) && rain != null) {
            rain3h = rain.getRain3h();
        }

        logger.info("RainCurrent: {}", rain3h);
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
        if (time.isAfter(begin) && time.isBefore(end) && clouds != null) {
            cloud = clouds.getAll();
        }

        logger.info("CloudCurrent: {}%", cloud);
        return cloud;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.morlinski.weather.openweathermap.Weather#temperature(java.time.
     * LocalDateTime, java.time.LocalDateTime)
     */
    @Override
    public double temperature(LocalDateTime begin, LocalDateTime end) {
        LocalDateTime time = convertTimestampToLocalDataTime(dt);
        
        double temperature = -1.0;
        if (time.isAfter(begin) && time.isBefore(end) && main != null) {
            temperature = main.getTemp();
        }

        logger.info("TemperatureCurrent: {}", temperature);
        return temperature;
    }
}
