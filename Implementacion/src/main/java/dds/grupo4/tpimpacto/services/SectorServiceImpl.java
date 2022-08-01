package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SectorServiceImpl extends BaseServiceImpl<Sector, SectorRepository> implements SectorService {

    public SectorServiceImpl(SectorRepository sectorRepository) {
        super(sectorRepository);
    }

    @Override
    @Transactional
    public Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion) {
        return repository.getByNombreYOrganizacion(nombreSector, razonSocialOrganizacion);
    }
}
