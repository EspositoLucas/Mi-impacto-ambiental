package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class TipoServicioContratadoRepository extends BaseRepository<TipoServicioContratado> {

    public TipoServicioContratadoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<TipoServicioContratado> getByNombre(String nombre) {
        String query = "FROM TipoServicioContratado tipo " +
                "WHERE tipo.nombre = :nombre";
        return entityManager.createQuery(query, TipoServicioContratado.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<TipoServicioContratado> getEntityClass() {
        return TipoServicioContratado.class;
    }
}
