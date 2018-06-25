package pl.morlinski.weather.openweathermap;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import pl.morlinski.weather.RequestBuilderExecutor;
import pl.morlinski.weather.RequestBuilderOperation;
import pl.morlinski.weather.RequestBuilderParam;

/**
 * Klasa tworząca zapytanie REST do serwisu openweathermap.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
public class RequestBuilder implements RequestBuilderOperation, RequestBuilderParam, RequestBuilderExecutor {
    public static final String UNITS_METRIC = "metric";

    public enum Operation {
        WEATHER("weather"), FORECAST_5_DAYS("forecast");

        private @Getter String operation;

        private Operation(String operation) {
            this.operation = operation;
        }
    }

    public enum Param {
        LOCATION("q"), UNITS("units");

        private @Getter String param;

        private Param(String param) {
            this.param = param;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);
    private static final String QUESTION_MARK = "?";
    private static final String AND_MARK = "&";
    private static final String EQUAL_MARK = "=";
    private static final String APPID_QUERY = "APPID=";

    private static final String URL = "http://api.openweathermap.org/data/2.5/";

    private Operation operation;
    private Optional<String> query;
    private String url;
    private final RestTemplate restTemplate;
    private final String apiKey;

    private RequestBuilder(RestTemplate restTemplate, String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.query = Optional.empty();
    }

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

        query = Optional.of(query.get() + param.getParam() + EQUAL_MARK + value + AND_MARK);

        return this;
    }

    @Override
    public RequestBuilderExecutor build() {
        url = URL + operation.getOperation();

        if (query.isPresent()) {
            url += query.get();
        } else {
            url += QUESTION_MARK;
        }

        url += APPID_QUERY + apiKey;

        logger.info("URL: {}", url);
        return this;
    }

    @Override
    public pl.morlinski.weather.Weather execute() {
        switch (operation) {
        case WEATHER:
            return restTemplate.getForObject(url, CurrentWeatherResponse.class);
        case FORECAST_5_DAYS:
            return restTemplate.getForObject(url, ForecastWeatherResponse.class);
        default:
            throw new IllegalArgumentException("No recognize operation:" + operation);
        }
    }

}
