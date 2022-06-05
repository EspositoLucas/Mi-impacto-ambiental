package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Organizacion;

import java.util.List;

public interface OrganizacionRepository {
    void save(Organizacion organizacion);

    List<Organizacion> getAll();
}
