package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;

import javax.persistence.EntityManager;
import java.util.Optional;

public class MedioDeTransporteRepositoryImpl extends BaseRepositoryImpl<MedioDeTransporte> implements MedioDeTransporteRepository{

    public MedioDeTransporteRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<MedioDeTransporte> getByNombre(String nombre) {
        String query = "FROM MedioDeTransporte medioDeTransporte " +
                "WHERE medioDeTrasnporte.nombre = :nombre";
        return entityManager.createQuery(query, MedioDeTransporte.class)
                .setParameter("nombre", nombre)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<MedioDeTransporte> getEntityClass() {
        return MedioDeTransporte.class;
    }
}
