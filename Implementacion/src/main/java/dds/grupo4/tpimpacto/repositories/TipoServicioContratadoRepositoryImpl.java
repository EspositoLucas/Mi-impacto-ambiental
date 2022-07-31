package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TipoServicioContratadoRepositoryImpl extends BaseRepositoryImpl<TipoServicioContratado> implements TipoServicioContratadoRepository {

    public TipoServicioContratadoRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<TipoServicioContratado> getEntityClass() {
        return TipoServicioContratado.class;
    }
}
