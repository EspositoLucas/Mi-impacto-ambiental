package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;

import javax.persistence.EntityManager;
import java.util.Optional;

public class TipoConsumoRepositoryImpl extends BaseRepositoryImpl<TipoConsumo> implements TipoConsumoRepository {

    public TipoConsumoRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<TipoConsumo> getByNombre(String nombre) {
        String query = "FROM TipoConsumo tipoConsumo" +
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
