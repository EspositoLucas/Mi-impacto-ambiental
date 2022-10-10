package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import dds.grupo4.tpimpacto.entities.medicion.RegistroCalculoHCDatoActividad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class RegistroCalculoHCDatoActividadRepository extends BaseRepository<RegistroCalculoHCDatoActividad> {

    public RegistroCalculoHCDatoActividadRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<RegistroCalculoHCDatoActividad> getRegistroCalculoHCParaPeriodo(long idOrganizacion, Periodicidad periodicidad, LocalDate periodo) {
        String query = "FROM RegistroCalculoHCDatoActividad registroCalculo " +
                "WHERE registroCalculo.organizacion.id = :idOrganizacion " +
                " AND registroCalculo.periodicidad = :periodicidad " +
                " AND year(registroCalculo.periodoImputacion) = year(:periodoImputacion) ";
        if (periodicidad == Periodicidad.MENSUAL) {
            query = query + " AND month(registroCalculo.periodoImputacion) = month(:periodoImputacion)";
        }
        return entityManager.createQuery(query, RegistroCalculoHCDatoActividad.class)
                .setParameter("idOrganizacion", idOrganizacion)
                .setParameter("periodicidad", periodicidad)
                .setParameter("periodoImputacion", periodo)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<RegistroCalculoHCDatoActividad> getEntityClass() {
        return RegistroCalculoHCDatoActividad.class;
    }
}
