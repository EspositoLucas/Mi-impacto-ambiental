package dds.grupo4.tpimpacto.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class EmailSenderImpl implements NotificationSender {

    private final JavaMailSender mailSender;

    public EmailSenderImpl(JavaMailSender mailSender) {
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

        boolean useHtml = true;
        messageHelper.setText(body, useHtml);

        mailSender.send(mimeMessage);
    }

}
