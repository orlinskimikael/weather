package pl.morlinski.weather.smsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;

/**
 * Komponent obsługujący wysyłanie SMS-ów.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
@Component
@Slf4j
public class SmsApi {

    /**
     * Fabryka tworząca SMS-y.
     */
    private SmsFactory smsFactory;

    /**
     * Inicjalizacja komponentu.
     * 
     * @param smsFactory
     *            Fabryka tworząca SMS-y.
     */
    @Autowired
    private SmsApi(SmsFactory smsFactory) {
        this.smsFactory = smsFactory;
    }

    /**
     * Wysłanie SMS-u.
     * 
     * @param message
     *            Wiadomość.
     * @param phoneNumber
     *            Numer telefonu na który będzie wysłany SMS.
     * @throws SmsapiException
     *             Błąd przy wysłaniu.
     */
    public void sendSMS(String message, String phoneNumber) throws SmsapiException {
        SMSSend action = smsFactory.actionSend().setSender("ECO").setText(message).setTo(phoneNumber);

        log.info("Message(lenght: {}): {};MSISDN: {}", message.length(), message, phoneNumber);
        StatusResponse result = action.execute();

        for (MessageResponse status : result.getList()) {
            log.info("{} {}", status.getNumber(), status.getStatus());
        }
    }
}
