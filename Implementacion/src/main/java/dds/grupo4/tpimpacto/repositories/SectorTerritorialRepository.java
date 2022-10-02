package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SectorTerritorialRepository extends BaseRepository<SectorTerritorial> {

    public SectorTerritorialRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<SectorTerritorial> getEntityClass() {
        return SectorTerritorial.class;
    }
}
