package pl.morlinski.weather.openweathermap;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import pl.morlinski.weather.Weather;
import pl.morlinski.weather.openweathermap.RequestBuilder.Operation;
import pl.morlinski.weather.openweathermap.RequestBuilder.Param;

public class RequestBuilderTest {

    @Test
    public void checkOperationValue() {
        // given
        // when
        // then
        assertThat(Operation.WEATHER.getValue()).isEqualTo("weather");
    }

    @Test
    public void checkParamValue() {
        // given
        // when
        // then
        assertThat(Param.LOCATION.getValue()).isEqualTo("q");
    }

    @Test
    public void checkSetOperationWithoutParam() {
        // given
        // when
        String url = RequestBuilder.create(null, null).setOperation(Operation.WEATHER).build().getUrl();
        // then
        assertThat(url).isNotNull().contains(Operation.WEATHER.getValue());
    }

    @Test
    public void checkAddParams() {
        // given
        // when
        String url = RequestBuilder.create(null, null).setOperation(Operation.WEATHER).addParam(Param.LOCATION, "test")
                .addParam(Param.UNITS, "test").build().getUrl();
        // then
        assertThat(url).isNotNull().contains("q=test&units=test");
    }

    @Test
    public void checkExecuteWeather() {
        // given
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(CurrentWeatherResponse.class)))
                .thenReturn(new CurrentWeatherResponse());
        // when
        Weather weather = RequestBuilder.create(restTemplate, null).setOperation(Operation.WEATHER).build().execute();
        // then
        assertThat(weather).isInstanceOf(CurrentWeatherResponse.class);
    }
    
    @Test
    public void checkExecuteForecast() {
        // given
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(ForecastWeatherResponse.class)))
                .thenReturn(new ForecastWeatherResponse());
        // when
        Weather weather = RequestBuilder.create(restTemplate, null).setOperation(Operation.FORECAST_5_DAYS).build()
                .execute();
        // then
        assertThat(weather).isInstanceOf(ForecastWeatherResponse.class);
    }
}
