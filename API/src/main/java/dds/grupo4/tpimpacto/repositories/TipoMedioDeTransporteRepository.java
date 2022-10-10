package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioDeTransporte;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TipoMedioDeTransporteRepository extends BaseRepository<TipoMedioDeTransporte> {

    public TipoMedioDeTransporteRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public TipoMedioDeTransporte getByNombre(String nombre) {
        String query = "FROM TipoMedioDeTransporte t " +
                "WHERE t.nombre = :nombre";
        return entityManager.createQuery(query, TipoMedioDeTransporte.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst().orElse(null);
    }

    @Override
    public Class<TipoMedioDeTransporte> getEntityClass() {
        return TipoMedioDeTransporte.class;
    }
}
