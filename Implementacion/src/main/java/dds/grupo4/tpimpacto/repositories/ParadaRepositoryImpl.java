package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ParadaRepositoryImpl extends BaseRepositoryImpl<Parada> implements ParadaRepository {
    public ParadaRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Parada> getEntityClass() {
        return null;
    }
}
