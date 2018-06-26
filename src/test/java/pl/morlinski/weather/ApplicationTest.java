package pl.morlinski.weather;

import static org.mockito.Mockito.when;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import pl.morlinski.weather.openweathermap.Clouds;
import pl.morlinski.weather.openweathermap.ForecastWeatherResponse;
import pl.morlinski.weather.openweathermap.List;
import pl.morlinski.weather.openweathermap.Main;
import pl.morlinski.weather.openweathermap.Rain;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.exception.SmsapiException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class})
@TestPropertySource(locations = "classpath:test.properties")
public class ApplicationTest {

    @MockBean
    private RestTemplate restTemplate;
    
    @MockBean
    private SmsFactory smsFactory;
    
    @Autowired
    private CommandLineRunner runner;
    
    @Test
    public void checkRunnerForecast() throws Exception {
        //given
        ForecastWeatherResponse response = new ForecastWeatherResponse();
        List list = new List();
        list.setDt(System.currentTimeMillis() / 1000);
        Main main = new Main();
        main.setTemp(2.0);
        list.setMain(main);
        Rain rain = new Rain();
        rain.setRain3h(2.0);
        list.setRain(rain);
        Clouds clouds = new Clouds();
        clouds.setAll(2.0);
        list.setClouds(clouds );
        response.setList(new List[] { list });
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(ForecastWeatherResponse.class))).thenReturn(response);
        SMSSend smsSend = new SMSSend() {

            @Override
            public SMSSend setText(String text) {
                return this;
            }

            @Override
            public SMSSend setSender(String sender) {
                return this;
            }

            @Override
            public SMSSend setTo(String to) {
                return this;
            }

            @Override
            public SendStatusResponse execute() throws SmsapiException {
                SendStatusResponse response = new SendStatusResponse(0, 0, new JSONArray());
                response.getList().add(new MessageResponse("id", "1", "number", "status", "error", "idx"));
                return response;
            }
            
        };
        when(smsFactory.actionSend()).thenReturn(smsSend);
        //when
        runner.run();
        //then
    }

}
