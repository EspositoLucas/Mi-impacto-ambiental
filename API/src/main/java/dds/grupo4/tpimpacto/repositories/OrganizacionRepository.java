package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrganizacionRepository extends BaseRepository<Organizacion> {

    public OrganizacionRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Organizacion> getByRazonSocial(String razonSocial) {
        String query = "FROM Organizacion org " +
                "WHERE org.razonSocial = :razonSocial";
        return entityManager.createQuery(query, Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultStream()
                .findFirst();
    }

    public List<String> getMailsDeContactos() {
        String query = "SELECT contacto.email " +
                "FROM Organizacion org " +
                "JOIN org.contactos contacto";
        return entityManager.createQuery(query, String.class)
                .getResultStream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Organizacion> getOrganizacionesConClasificacion(Clasificacion clasificacion) {
        String query = "FROM Organizacion org " +
                "WHERE org.clasificacion = :clasificacion";
        return entityManager.createQuery(query, Organizacion.class)
                .setParameter("clasificacion", clasificacion)
                .getResultList();
    }

    @Override
    public Class<Organizacion> getEntityClass() {
        return Organizacion.class;
    }
}
