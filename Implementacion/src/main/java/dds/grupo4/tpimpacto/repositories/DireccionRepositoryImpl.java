package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class DireccionRepositoryImpl extends BaseRepositoryImpl<Direccion> implements DireccionRepository {

    public DireccionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Direccion> getByDireccionEntera(
            String calle,
            String altura,
            String pais,
            String provincia,
            String municipio,
            String localidad,
            String barrio,
            int codigoPostal
    ) {
        String query = "FROM Direccion direccion " +
                "WHERE direccion.calle = :calle " +
                "AND direccion.altura = :altura " +
                "AND direccion.pais = :pais " +
                "AND direccion.provincia = :provincia " +
                "AND direccion.municipio = :municipio " +
                "AND direccion.localidad = :localidad " +
                "AND direccion.barrio = :barrio " +
                "AND direccion.codigoPostal = :codigoPostal";
        return entityManager.createQuery(query, Direccion.class)
                .setParameter("calle", calle)
                .setParameter("altura", altura)
                .setParameter("pais", pais)
                .setParameter("provincia", provincia)
                .setParameter("municipio", municipio)
                .setParameter("localidad", localidad)
                .setParameter("barrio", barrio)
                .setParameter("codigoPostal", codigoPostal)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<Direccion> getEntityClass() {
        return Direccion.class;
    }
}
