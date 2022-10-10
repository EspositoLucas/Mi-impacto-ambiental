package dds.grupo4.tpimpacto.services;

import java.util.List;

public class EmailContactsFinder implements NotificationContactsFinder {
    @Override
    public List<String> findContacts(OrganizacionService organizacionService) {
        return organizacionService.getMailsDeContactos();
    }
}
