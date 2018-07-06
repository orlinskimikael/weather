package pl.morlinski.weather.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
class EmailSenderImpl implements EmailSender {
    
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    @Override
    public void sendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(content);
        
        log.info("Email({})[{}]{}", to, title, content);
        javaMailSender.send(mail);
    }
    
    
}
