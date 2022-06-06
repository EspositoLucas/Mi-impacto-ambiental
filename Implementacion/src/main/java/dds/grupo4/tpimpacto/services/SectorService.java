package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.Sector;

import java.util.Optional;

public interface SectorService extends BaseService<Sector> {
    Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion);
}
