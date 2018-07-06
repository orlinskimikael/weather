package pl.morlinski.weather.email;

import static org.junit.Assert.assertTrue;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.morlinski.weather.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@TestPropertySource(locations = "classpath:test.properties")
public class EmailSenderImplTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    public void checkSendEmail() throws MessagingException {
        // given
        // when
        emailSender.sendEmail("orlinskimikael@gmail.com", "test", "test");
        // then
        assertTrue(true);
    }

}
