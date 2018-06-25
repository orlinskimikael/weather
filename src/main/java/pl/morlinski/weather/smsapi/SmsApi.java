package pl.morlinski.weather.smsapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;

@Component
public class SmsApi {
    private static final Logger logger = LoggerFactory.getLogger(SmsApi.class);

    private SmsFactory smsFactory;

    @Autowired
    private SmsApi(SmsFactory smsFactory) {
        this.smsFactory = smsFactory;
    }

    public void sendSMS(String message, String phoneNumber) throws SmsapiException {
        SMSSend action = smsFactory.actionSend().setSender("ECO").setText(message).setTo(phoneNumber);

        logger.info("Message(lenght: {}): {};MSISDN: {}",message.length(), message, phoneNumber);
        StatusResponse result = action.execute();

        for (MessageResponse status : result.getList()) {
            logger.info(status.getNumber() + " " + status.getStatus());
        }
    }
}
