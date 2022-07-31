package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TrayectoRepositoryImpl extends BaseRepositoryImpl<Trayecto> implements TrayectoRepository {

    public TrayectoRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Trayecto> getEntityClass() {
        return Trayecto.class;
    }
}
