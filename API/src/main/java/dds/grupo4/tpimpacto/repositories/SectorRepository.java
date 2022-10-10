package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class SectorRepository extends BaseRepository<Sector> {

    public SectorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Sector> getByNombreYOrganizacion(String nombreSector, String razonSocialOrganizacion) {
        String query = "FROM Sector sector " +
                "WHERE sector.nombre = :nombreSector AND sector.organizacion.razonSocial = :razonSocialOrganizacion";
        return entityManager.createQuery(query, Sector.class)
                .setParameter("nombreSector", nombreSector)
                .setParameter("razonSocialOrganizacion", razonSocialOrganizacion)
                .getResultStream()
                .findFirst();
    }

    public Optional<Sector> getByNombre(String nombreSector) {
        String query = "FROM Sector sector " +
                "WHERE sector.nombre = :nombreSector";
        return entityManager.createQuery(query, Sector.class)
                .setParameter("nombreSector", nombreSector)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<Sector> getEntityClass() {
        return Sector.class;
    }
}
