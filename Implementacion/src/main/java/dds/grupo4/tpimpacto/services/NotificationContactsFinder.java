package dds.grupo4.tpimpacto.services;

import java.util.List;

public interface NotificationContactsFinder {
    List<String> findContacts(OrganizacionService organizacionService);
}
