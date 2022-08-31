package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class SolicitudRepository extends BaseRepository<Solicitud> {

    public SolicitudRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Solicitud> getEntityClass() {
        return Solicitud.class;
    }

}
