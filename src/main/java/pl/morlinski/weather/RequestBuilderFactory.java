package pl.morlinski.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.morlinski.weather.openweathermap.RequestBuilder;

@Component
public class RequestBuilderFactory {
    
    private RestTemplate restTemplate;
    private String apiKey;
    
    @Autowired
    private RequestBuilderFactory(RestTemplate restTemplate, @Value("${openweathermap.api_key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }
    
    public RequestBuilderOperation create() {
        return RequestBuilder.create(restTemplate, apiKey);
    }
}
