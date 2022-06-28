package dds.grupo4.tpimpacto.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(List<String> to, String subject, String body) throws MessagingException {
        String[] arrayTo = to.toArray(new String[0]);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        messageHelper.setFrom("grupo4dds2022@hotmail.com");
        messageHelper.setTo(arrayTo);
        messageHelper.setSubject(subject);
        messageHelper.setText(body, true);
        mailSender.send(mimeMessage);
    }

}
