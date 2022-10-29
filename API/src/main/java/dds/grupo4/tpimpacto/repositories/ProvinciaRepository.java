package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.geo.Provincia;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProvinciaRepository extends BaseRepository<Provincia> {

    public ProvinciaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Provincia> getEntityClass() {
        return Provincia.class;
    }
}
