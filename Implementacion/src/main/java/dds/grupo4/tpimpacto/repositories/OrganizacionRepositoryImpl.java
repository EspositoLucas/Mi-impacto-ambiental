package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrganizacionRepositoryImpl extends BaseRepositoryImpl<Organizacion> implements OrganizacionRepository {

    public OrganizacionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Organizacion> getByRazonSocial(String razonSocial) {
        String query = "FROM Organizacion org " +
                "WHERE org.razonSocial = :razonSocial";
        return entityManager.createQuery(query, Organizacion.class)
                .setParameter("razonSocial", razonSocial)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<String> getMailsDeContactos() {
        String query = "SELECT contacto.email " +
                "FROM Organizacion org " +
                "JOIN org.contactos contacto";
        return entityManager.createQuery(query, String.class)
                .getResultStream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Class<Organizacion> getEntityClass() {
        return Organizacion.class;
    }
}
