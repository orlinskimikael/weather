package pl.morlinski.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.morlinski.weather.openweathermap.RequestBuilder;

/**
 * Fabryka budowania zapytania o pogodę.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
@Component
public class RequestBuilderFactory {

    /**
     * REST klient.
     */
    private RestTemplate restTemplate;
    /**
     * Klucz dla serwisu https://openweathermap.org/
     */
    private String apiKey;

    /**
     * Utowrzenie fabryki. Pobranie konfiguracji dla serwisów.
     * 
     * @param restTemplate
     *            REST klient.
     * @param apiKey
     *            Klucz dla serwisu https://openweathermap.org/
     */
    @Autowired
    private RequestBuilderFactory(RestTemplate restTemplate, @Value("${openweathermap.api_key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    /**
     * Metoda fabryki tworząca zapytanie o pogodę.
     * 
     * @return {@link RequestBuilderOperation}
     */
    public RequestBuilderOperation create() {
        return RequestBuilder.create(restTemplate, apiKey);
    }
}
