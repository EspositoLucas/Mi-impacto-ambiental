package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class SectorRepositoryImpl extends BaseRepositoryImpl<Sector> implements SectorRepository {

    public SectorRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion) {
        String query = "FROM Sector sector " +
                "WHERE sector.nombre = :nombreSector AND sector.organizacion.razonSocial = :razonSocialOrganizacion";
        return entityManager.createQuery(query, Sector.class)
                .setParameter("nombreSector", nombreSector)
                .setParameter("razonSocialOrganizacion", razonSocialOrganizacion)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<Sector> getEntityClass() {
        return Sector.class;
    }
}
