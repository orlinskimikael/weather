package pl.morlinski.weather.email;

import javax.mail.MessagingException;

public interface EmailSender {
    void sendEmail(String to, String subject, String cotent) throws MessagingException;
}
