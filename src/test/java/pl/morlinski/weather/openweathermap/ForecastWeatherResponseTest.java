package pl.morlinski.weather.openweathermap;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class ForecastWeatherResponseTest {

    @Test
    public void checkRainNoBetweenAfter() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        response.setList(new List[] { list });
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        // when
        double result = response.rain(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkRainNoBetweenBefore() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        response.setList(new List[] { list });
        LocalDateTime after = LocalDateTime.now().plusDays(1);
        // when
        double result = response.rain(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkRainBetweenNull() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        list.setRain(null);
        response.setList(new List[] { list });
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.rain(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void checkRainBetweenNotNull() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        Rain rain = new Rain();
        rain.setRain3h(1.0);
        list.setRain(rain);
        response.setList(new List[] { list });
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.rain(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void checkTemperatureNoBetweenAfter() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        response.setList(new List[] { list });
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        // when
        double result = response.temperature(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkTemperatureNoBetweenBefore() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        response.setList(new List[] { list });
        LocalDateTime after = LocalDateTime.now().plusDays(1);
        // when
        double result = response.temperature(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkTemperatureBetweenNull() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        list.setMain(null);
        response.setList(new List[] { list });
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.temperature(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void checkTemperatureBetweenNotNull() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        Main temperature = new Main();
        temperature.setTemp(1.0);
        list.setMain(temperature);
        response.setList(new List[] { list });
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.temperature(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void checkCloudsNoBetweenAfter() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        response.setList(new List[] { list });
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        // when
        double result = response.cloud(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkCloudsNoBetweenBefore() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        response.setList(new List[] { list });
        LocalDateTime after = LocalDateTime.now().plusDays(1);
        // when
        double result = response.cloud(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkCloudsBetweenNull() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        list.setClouds(null);
        response.setList(new List[] { list });
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.cloud(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void checkCloudsBetweenNotNull() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        Clouds cloud = new Clouds();
        cloud.setAll(1.0);
        list.setClouds(cloud);
        response.setList(new List[] { list });
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.cloud(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void checkCity() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        // when
        response.setCity(new City());
        // then
        assertThat(response.getCity()).isNotNull();
    }

    @Test
    public void checkList() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        // when
        response.setList(new List[] {});
        // then
        assertThat(response.getList()).isNotNull();
    }

    @Test
    public void checkCode() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        // when
        response.setCode(new String());
        // then
        assertThat(response.getCode()).isNotNull();
    }

    @Test
    public void checkMessage() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        // when
        response.setMessage(new String());
        // then
        assertThat(response.getMessage()).isNotNull();
    }

    @Test
    public void checkCnt() {
        // given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        // when
        response.setCnt(new String());
        // then
        assertThat(response.getCnt()).isNotNull();
    }

    @Test
    public void checkForecastWeatherResponse() {
        // given
        // when
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        // then
        assertThat(response).isNotNull();
        assertThat(response.toString()).isNotNull();
    }

}
