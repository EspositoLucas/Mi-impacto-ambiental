package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class EspacioRepositoryImpl extends BaseRepositoryImpl<Espacio> implements EspacioRepository {

    public EspacioRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Espacio> getEntityClass() {
        return Espacio.class;
    }
}
