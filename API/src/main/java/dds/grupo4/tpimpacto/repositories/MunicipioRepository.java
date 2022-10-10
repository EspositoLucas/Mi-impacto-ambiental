package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.geo.Municipio;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MunicipioRepository extends BaseRepository<Municipio> {

    public MunicipioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Municipio> getEntityClass() {
        return Municipio.class;
    }
}
