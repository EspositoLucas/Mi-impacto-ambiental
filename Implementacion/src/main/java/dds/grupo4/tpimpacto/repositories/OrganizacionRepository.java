package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Organizacion;

import java.util.Optional;

public interface OrganizacionRepository extends BaseRepository<Organizacion> {
    Optional<Organizacion> getByRazonSocial(String razonSocial);
}
