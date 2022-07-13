package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Sector;

import java.util.Optional;

public interface SectorRepository extends BaseRepository<Sector> {
    Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion);
}
