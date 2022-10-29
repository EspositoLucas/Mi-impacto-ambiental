package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ParadaRepository extends BaseRepository<Parada> {
    public ParadaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Parada> getEntityClass() {
        return null;
    }
}
