package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SolicitudRepository extends BaseRepository<Solicitud> {

    public SolicitudRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Solicitud> solicitudesParaOrganizacion(long idOrganizacion) {
        String query = "SELECT s " +
                "FROM Solicitud s " +
                "WHERE s.sector.organizacion.id = :idOrganizacion ";
        return entityManager.createQuery(query, Solicitud.class)
                .setParameter("idOrganizacion", idOrganizacion)
                .getResultList();
    }

    @Override
    public Class<Solicitud> getEntityClass() {
        return Solicitud.class;
    }

}
