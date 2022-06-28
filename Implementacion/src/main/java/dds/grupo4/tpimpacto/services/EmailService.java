package dds.grupo4.tpimpacto.services;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void send(List<String> to, String subject, String body) throws MessagingException;
}
