package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.repositories.SectorTerritorialRepository;
import org.springframework.stereotype.Service;

@Service
public class SectorTerritorialService extends BaseService<SectorTerritorial, SectorTerritorialRepository> {
    public SectorTerritorialService(SectorTerritorialRepository repository) {
        super(repository);
    }
}
