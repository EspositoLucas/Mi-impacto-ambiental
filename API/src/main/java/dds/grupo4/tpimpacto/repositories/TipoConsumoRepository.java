package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class TipoConsumoRepository extends BaseRepository<TipoConsumo> {

    public TipoConsumoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<TipoConsumo> getByNombre(String nombre) {
        String query = "FROM TipoConsumo tipoConsumo " +
                "WHERE tipoConsumo.nombre = :nombre";
        return entityManager.createQuery(query, TipoConsumo.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<TipoConsumo> getEntityClass() {
        return TipoConsumo.class;
    }
}
