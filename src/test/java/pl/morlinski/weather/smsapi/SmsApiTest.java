package pl.morlinski.weather.smsapi;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.morlinski.weather.Application;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.exception.SmsapiException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class})
@TestPropertySource(locations = "classpath:test.properties")
public class SmsApiTest {

    @MockBean
    private SmsFactory smsFactory;

    @Autowired
    private SmsApi smsApi;
    
    
    @Test
    public void checkSendSms() throws SmsapiException {
        // given
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
        // when
        smsApi.sendSMS("test", "xxx");
        // then
        assertTrue(true);
    }

}
