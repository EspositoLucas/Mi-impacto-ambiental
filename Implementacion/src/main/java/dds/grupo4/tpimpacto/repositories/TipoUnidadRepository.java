package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.units.TipoUnidad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class TipoUnidadRepository extends BaseRepository<TipoUnidad> {

    public TipoUnidadRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<TipoUnidad> getByNombre(String nombre) {
        String query = "from TipoUnidad tu where tu.nombre = :nombre";
        return entityManager.createQuery(query, TipoUnidad.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findAny();
    }

    @Override
    public Class<TipoUnidad> getEntityClass() {
        return TipoUnidad.class;
    }
}
