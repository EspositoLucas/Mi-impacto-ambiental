package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class DireccionRepository extends BaseRepository<Direccion> {

    public DireccionRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Direccion> getByDireccionEntera(
            String calle,
            String altura,
            long idLocalidad
    ) {
        String query = "FROM Direccion direccion " +
                "WHERE direccion.calle = :calle " +
                "AND direccion.altura = :altura " +
                "AND direccion.localidad.id = :idLocalidad ";
        return entityManager.createQuery(query, Direccion.class)
                .setParameter("calle", calle)
                .setParameter("altura", altura)
                .setParameter("idLocalidad", idLocalidad)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<Direccion> getEntityClass() {
        return Direccion.class;
    }
}
