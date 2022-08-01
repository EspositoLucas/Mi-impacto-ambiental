package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;

import javax.persistence.EntityManager;

public class SolicitudRepositoryImpl extends BaseRepositoryImpl<Solicitud> implements SolicitudRepository {

    public SolicitudRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Solicitud> getEntityClass() {
        return Solicitud.class;
    }
    
}
