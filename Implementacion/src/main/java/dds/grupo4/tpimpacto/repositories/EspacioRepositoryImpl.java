package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Espacio;

import javax.persistence.EntityManager;

public class EspacioRepositoryImpl extends BaseRepositoryImpl<Espacio> implements EspacioRepository {

    public EspacioRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Espacio> getEntityClass() {
        return Espacio.class;
    }
}
