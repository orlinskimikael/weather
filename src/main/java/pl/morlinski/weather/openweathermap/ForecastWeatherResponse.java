package pl.morlinski.weather.openweathermap;

import static pl.morlinski.weather.DataConverter.convertTimestampToLocalDataTime;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import pl.morlinski.weather.Weather;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastWeatherResponse implements Weather {
    private static final Logger logger = LoggerFactory.getLogger(ForecastWeatherResponse.class);

    private String code;
    private String message;
    private City city;
    private String cnt;
    private List[] list;

    /*
     * (non-Javadoc)
     * 
     * @see
     * pl.morlinski.weather.openweathermap.Weather#rain(java.time.LocalDateTime,
     * java.time.LocalDateTime)
     */
    @Override
    public double rain(LocalDateTime begin, LocalDateTime end) {
        AtomicInteger count = new AtomicInteger(0);

        double rainSum = Arrays.stream(list).mapToDouble(list -> {
            LocalDateTime time = convertTimestampToLocalDataTime(list.getDt());

            if (time.isAfter(begin) && time.isBefore(end) && list.getRain() != null) {
                count.incrementAndGet();
                return list.getRain().getRain3h();
            } else {
                return 0.0;
            }
        }).sum();

        double rainAvg = rainSum / count.get();

        logger.info("RainAvg: {}/{} = {}", rainSum, count.get(), rainAvg);
        return rainAvg;
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
        AtomicInteger count = new AtomicInteger(0);

        double temperatureSum = Arrays.stream(list).mapToDouble(list -> {
            LocalDateTime time = convertTimestampToLocalDataTime(list.getDt());

            if (time.isAfter(begin) && time.isBefore(end) && list.getMain() != null) {
                count.incrementAndGet();
                return list.getMain().getTemp();
            } else {
                return 0.0;
            }
        }).sum();

        double temperatureAvg = temperatureSum / count.get();

        logger.info("TemperatureAvg: {}/{} = {}", temperatureSum, count.get(), temperatureAvg);
        return temperatureAvg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.morlinski.weather.openweathermap.Weather#clouds(java.time.
     * LocalDateTime, java.time.LocalDateTime)
     */
    @Override
    public double cloud(LocalDateTime begin, LocalDateTime end) {
        AtomicInteger count = new AtomicInteger(0);

        double cloudsSum = Arrays.stream(list).mapToDouble(list -> {
            LocalDateTime time = convertTimestampToLocalDataTime(list.getDt());

            if (time.isAfter(begin) && time.isBefore(end) && list.getClouds() != null) {
                count.incrementAndGet();
                return list.getClouds().getAll();
            } else {
                return 0.0;
            }
        }).sum();

        double cloudAvg = cloudsSum / count.get();

        logger.info("CloudAvg: {}/{} = {}%", cloudsSum, count.get(), cloudAvg);
        return cloudAvg;
    }
}
