package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TrayectoRepository extends BaseRepository<Trayecto> {

    public TrayectoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Trayecto> getEntityClass() {
        return Trayecto.class;
    }
}
