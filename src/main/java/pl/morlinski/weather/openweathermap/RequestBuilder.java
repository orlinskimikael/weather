package pl.morlinski.weather.openweathermap;

import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.morlinski.weather.RequestBuilderExecutor;
import pl.morlinski.weather.RequestBuilderOperation;
import pl.morlinski.weather.RequestBuilderParam;
import pl.morlinski.weather.Weather;

/**
 * Klasa tworząca zapytanie REST do serwisu https://openweathermap.org/.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
@Slf4j
public class RequestBuilder implements RequestBuilderOperation, RequestBuilderParam, RequestBuilderExecutor {
    public static final String UNITS_METRIC = "metric";

    /**
     * Lista możliwych operacji do wykonania.
     */
    public enum Operation {
        WEATHER("weather"), FORECAST_5_DAYS("forecast");

        private @Getter String value;

        private Operation(String value) {
            this.value = value;
        }
    }

    /**
     * Lista parametrów do ustawienia.
     */
    public enum Param {
        LOCATION("q"), UNITS("units");

        private @Getter String value;

        private Param(String value) {
            this.value = value;
        }
    }

    /**
     * STAŁE
     */
    private static final String QUESTION_MARK = "?";
    private static final String AND_MARK = "&";
    private static final String EQUAL_MARK = "=";
    private static final String APPID_QUERY = "APPID=";
    /**
     * Link do serwisu.
     */
    private static final String URL = "http://api.openweathermap.org/data/2.5/";
    /**
     * Operacja do wykonania.
     */
    private Operation operation;
    /**
     * Parametry żądania.
     */
    private Optional<String> query;
    /**
     * Pełny adres żądania.
     */
    private String fullUrl;
    /**
     * REST klient.
     */
    private final RestTemplate restTemplate;
    /**
     * Klucz serwisu.
     */
    private final String apiKey;

    /**
     * Inicjalizacja żądania.
     * 
     * @param restTemplate
     *            REST klient.
     * @param apiKey
     *            Klucz serwisu.
     */
    private RequestBuilder(RestTemplate restTemplate, String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.query = Optional.empty();
    }

    /**
     * Metoda tworząca żądanie.
     * 
     * @param restTemplate
     *            REST klient.
     * @param apiKey
     *            Klucz serwisu.
     * @return Pierwszy etap tworzenia żądania {@link RequestBuilderOperation}
     */
    public static RequestBuilderOperation create(RestTemplate restTemplate, String apiKey) {
        return new RequestBuilder(restTemplate, apiKey);
    }

    @Override
    public RequestBuilderParam setOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    @Override
    public RequestBuilderParam addParam(Param param, String value) {
        if (!query.isPresent()) {
            query = Optional.of(QUESTION_MARK);
        }

        query = Optional.of(query.get() + param.getValue() + EQUAL_MARK + value + AND_MARK);
        return this;
    }

    @Override
    public RequestBuilderExecutor build() {
        fullUrl = URL + operation.getValue();

        if (query.isPresent()) {
            fullUrl += query.get();
        } else {
            fullUrl += QUESTION_MARK;
        }

        fullUrl += APPID_QUERY + apiKey;

        log.info("FullURL: {}", fullUrl);
        return this;
    }

    @Override
    public pl.morlinski.weather.Weather execute() {
        Weather weather = null;
        switch (operation) {
        case WEATHER:
            weather = restTemplate.getForObject(fullUrl, CurrentWeatherResponse.class);
            break;
        case FORECAST_5_DAYS:
            weather = restTemplate.getForObject(fullUrl, ForecastWeatherResponse.class);
            break;
        default:
            throw new IllegalArgumentException("No recognize operation:" + operation);
        }

        log.info("{}", weather);
        return weather;
    }

    @Override
    public String getUrl() {
        return fullUrl;
    }

}
