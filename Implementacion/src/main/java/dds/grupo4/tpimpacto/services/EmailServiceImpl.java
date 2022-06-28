package dds.grupo4.tpimpacto.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(List<String> to, String subject, String body) {
        String[] arrayTo = to.toArray(new String[0]);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("grupo4dds2022@hotmail.com");
        message.setTo(arrayTo);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
