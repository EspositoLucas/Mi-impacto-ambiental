package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.geo.Pais;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PaisRepository extends BaseRepository<Pais> {

    public PaisRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Pais> getEntityClass() {
        return Pais.class;
    }
}
