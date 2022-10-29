package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.geo.Localidad;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LocalidadRepository extends BaseRepository<Localidad> {

    public LocalidadRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Localidad getByNombre(String nombre) {
        String query = "from Localidad l " +
                "where l.nombre = :nombre";
        return entityManager.createQuery(query, Localidad.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Class<Localidad> getEntityClass() {
        return Localidad.class;
    }
}
