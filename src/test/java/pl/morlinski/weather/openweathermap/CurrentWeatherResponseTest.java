package pl.morlinski.weather.openweathermap;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class CurrentWeatherResponseTest {

    @Test
    public void checkRainNoBetweenAfter() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        // when
        double result = response.rain(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkRainNoBetweenBefore() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        LocalDateTime before = LocalDateTime.now().plusDays(1);
        // when
        double result = response.rain(before, before);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkRainBetweenNull() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        response.setRain(null);
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.rain(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void checkRainBetweenNotNull() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        Rain rain = new Rain();
        rain.setRain3h(1.0);
        response.setRain(rain);
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.rain(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void checkCloudNoBetweenAfter() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        // when
        double result = response.cloud(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkCloudNoBetweenBefore() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        LocalDateTime before = LocalDateTime.now().plusDays(1);
        // when
        double result = response.cloud(before, before);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkCloudBetweenNull() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        response.setClouds(null);
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.cloud(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void checkCloudBetweenNotNull() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        Clouds cloud = new Clouds();
        cloud.setAll(1.0);
        response.setClouds(cloud);
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.cloud(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void checkTemperatureNoBetweenAfter() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        LocalDateTime after = LocalDateTime.now().minusDays(1);
        // when
        double result = response.temperature(after, after);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkTemperatureNoBetweenBefore() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        LocalDateTime before = LocalDateTime.now().plusDays(1);
        // when
        double result = response.temperature(before, before);
        // then
        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    public void checkTemperatureBetweenNull() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        response.setMain(null);
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.temperature(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void checkTemperatureBetweenNotNull() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        response.setDt(System.currentTimeMillis() / 1000);
        Main main = new Main();
        main.setTemp(1.0);
        response.setMain(main);
        LocalDateTime now = LocalDateTime.now();
        // when
        double result = response.temperature(now.minusDays(1), now.plusDays(1));
        // then
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    public void checkCoord() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setCoord(new Coord());
        // then
        assertThat(response.getCoord()).isNotNull();
    }

    @Test
    public void checkWeather() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setWeather(new Weather[] {});
        // then
        assertThat(response.getWeather()).isNotNull();
    }

    @Test
    public void checkBase() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setBase(new String());
        // then
        assertThat(response.getBase()).isNotNull();
    }

    @Test
    public void checkMain() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setMain(new Main());
        // then
        assertThat(response.getMain()).isNotNull();
    }

    @Test
    public void checkWind() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setWind(new Wind());
        // then
        assertThat(response.getWind()).isNotNull();
    }

    @Test
    public void checkClouds() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setClouds(new Clouds());
        // then
        assertThat(response.getClouds()).isNotNull();
    }

    @Test
    public void checkRain() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setRain(new Rain());
        // then
        assertThat(response.getRain()).isNotNull();
    }

    @Test
    public void checkSnow() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setSnow(new Snow());
        // then
        assertThat(response.getSnow()).isNotNull();
    }

    @Test
    public void checkDt() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setDt(System.currentTimeMillis());
        // then
        assertThat(response.getDt()).isNotNull();
    }

    @Test
    public void checkSys() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setSys(new Sys());
        // then
        assertThat(response.getSys()).isNotNull();
    }

    @Test
    public void checkId() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setId(new String());
        // then
        assertThat(response.getId()).isNotNull();
    }

    @Test
    public void checkName() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setName(new String());
        // then
        assertThat(response.getName()).isNotNull();
    }

    @Test
    public void checkCod() {
        // given
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        // when
        response.setCod(new String());
        // then
        assertThat(response.getCod()).isNotNull();
    }
    
    @Test
    public void checkCurrentWeatherResponse() {
        //given
        //when
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        //then
        assertThat(response).isNotNull();
        assertThat(response.toString()).isNotNull();
    }

}
