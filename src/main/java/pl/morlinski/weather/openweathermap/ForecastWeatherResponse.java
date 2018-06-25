package pl.morlinski.weather.openweathermap;

import static pl.morlinski.weather.DataUtils.convertTimestampToLocalDataTime;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.morlinski.weather.DataUtils;
import pl.morlinski.weather.Weather;

/**
 * Odpowiedz dla zapytania o prognozę pogody na 5 dni z rozdzielczością 3
 * godzin.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class ForecastWeatherResponse implements Weather {
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

        double rainSum = Arrays.stream(list).mapToDouble(item -> {
            LocalDateTime time = convertTimestampToLocalDataTime(item.getDt());

            if (DataUtils.timeIsBetween(time, begin, end)) {
                count.incrementAndGet();
                if (item.getRain() == null) {
                    return 0.0;
                } else {
                    return item.getRain().getRain3h();
                }
            } else {
                return 0.0;
            }
        }).sum();

        double rainAvg = rainSum / count.get();

        log.info("RainAvg: {}/{} = {}", rainSum, count.get(), rainAvg);
        return rainAvg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see pl.morlinski.weather.openweathermap.Weather#temperature(java.time.
     * LocalDateTime, java.time.LocalDateTime)
     */
    @Override
    public double temperature(LocalDateTime begin, LocalDateTime end) {
        AtomicInteger count = new AtomicInteger(0);

        double temperatureSum = Arrays.stream(list).mapToDouble(item -> {
            LocalDateTime time = convertTimestampToLocalDataTime(item.getDt());

            if (DataUtils.timeIsBetween(time, begin, end)) {
                count.incrementAndGet();
                if (item.getMain() == null) {
                    return 0.0;
                } else {
                    return item.getMain().getTemp();
                }
            } else {
                return 0.0;
            }
        }).sum();

        double temperatureAvg = temperatureSum / count.get();

        log.info("TemperatureAvg: {}/{} = {}", temperatureSum, count.get(), temperatureAvg);
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

        double cloudsSum = Arrays.stream(list).mapToDouble(item -> {
            LocalDateTime time = convertTimestampToLocalDataTime(item.getDt());

            if (DataUtils.timeIsBetween(time, begin, end) && item.getClouds() != null) {
                count.incrementAndGet();
                if (item.getClouds() == null) {
                    return 0.0;
                } else {
                    return item.getClouds().getAll();
                }
            } else {
                return 0.0;
            }
        }).sum();

        double cloudAvg = cloudsSum / count.get();

        log.info("CloudAvg: {}/{} = {}%", cloudsSum, count.get(), cloudAvg);
        return cloudAvg;
    }
}
