package dds.grupo4.tpimpacto.services;

import java.util.List;

public interface EmailService {
    void send(List<String> to, String subject, String body);
}
