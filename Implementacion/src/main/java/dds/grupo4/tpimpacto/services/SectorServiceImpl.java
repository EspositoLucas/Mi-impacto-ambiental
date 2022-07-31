package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    public SectorServiceImpl(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public void save(Sector obj) {
        if (sectorRepository.getAll().contains(obj)) {
            sectorRepository.merge(obj);
        } else {
            sectorRepository.save(obj);
        }
    }

    @Override
    public List<Sector> getAll() {
        return sectorRepository.getAll();
    }

    @Override
    public Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion) {
        return sectorRepository.getByNombreYOrganizacion(nombreSector, razonSocialOrganizacion);
    }
}
