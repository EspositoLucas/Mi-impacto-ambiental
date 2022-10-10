package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class FactorDeEmisionRepository extends BaseRepository<FactorDeEmision> {

    public FactorDeEmisionRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<FactorDeEmision> getEntityClass() {
        return FactorDeEmision.class;
    }
}
