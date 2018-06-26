package pl.morlinski.weather;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pl.morlinski.weather.openweathermap.RequestBuilder;
import pl.morlinski.weather.openweathermap.RequestBuilder.Operation;
import pl.morlinski.weather.openweathermap.RequestBuilder.Param;
import pl.morlinski.weather.smsapi.SmsApi;
import pl.smsapi.BasicAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.exception.ClientException;

/**
 * Klasa uruchamiająca aplikację.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
@SpringBootApplication
@Slf4j
public class Application {
    /**
     * Metoda main aplikacji.
     * 
     * @param args
     *            Lista parametrów wejściowych.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * Fabryka budowania zapytania o pogodę.
     */
    @Autowired
    private RequestBuilderFactory requestBuilderFactory;

    /**
     * Narzędzie wysyłania SMS-ów.
     */
    @Autowired
    private SmsApi smsApi;

    /**
     * Komponent umożliwia wykonywanie zapytań REST.
     * 
     * @param builder
     * @return Klient serwisu REST.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Komponent umożliwia wysyłanie SMS-ów.
     * 
     * Oznaczenie błędów - ClientException:
     * 
     * <pre>
     * 101 Niepoprawne lub brak danych autoryzacji.
     * 102 Nieprawidłowy login lub hasło
     * 103 Brak punków dla tego użytkownika
     * 105 Błędny adres IP
     * 110 Usługa nie jest dostępna na danym koncie
     * 1000 Akcja dostępna tylko dla użytkownika głównego
     * 1001 Nieprawidłowa akcja
     * </pre>
     * 
     * @return Fabryka tworzenia SMS-ów.
     * @throws ClientException
     *             Błąd utworzenia fabryki.
     */
    @Bean
    public SmsFactory smsFactory(@Value("${smsapi.passwd_hash}") String passwd, @Value("${smsapi.user}") String user)
            throws ClientException {
        BasicAuthClient client = new BasicAuthClient(user, passwd);
        return new SmsFactory(client);
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate, @Value("${smsapi.msisdn}") String phoneNumber) {
        return args -> {
            String location = "Warsaw,pl";

            Weather forecast = requestBuilderFactory.create().setOperation(Operation.FORECAST_5_DAYS)
                    .addParam(Param.LOCATION, location).addParam(Param.UNITS, RequestBuilder.UNITS_METRIC).build()
                    .execute();

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nextDayStart = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 4, 0);
            LocalDateTime nextDayEnd = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 18, 0);

            String message = "Rain: " + forecast.rain(nextDayStart, nextDayEnd) + "mm;Cloud: "
                    + forecast.cloud(nextDayStart, nextDayEnd) + "%;Temp: "
                    + forecast.temperature(nextDayStart, nextDayEnd) + "C;Date:" + nextDayStart.toString() + " - "
                    + nextDayEnd.toString();
            log.info("message: {}", message);

            smsApi.sendSMS(message, phoneNumber);
        };
    }

}
