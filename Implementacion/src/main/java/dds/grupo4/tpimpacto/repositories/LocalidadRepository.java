package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.geo.Localidad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LocalidadRepository extends BaseRepository<Localidad> {

    public LocalidadRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Localidad> getEntityClass() {
        return Localidad.class;
    }
}
