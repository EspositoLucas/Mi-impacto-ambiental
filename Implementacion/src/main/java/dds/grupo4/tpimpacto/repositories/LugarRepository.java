package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LugarRepository extends BaseRepository<Lugar> {

    public LugarRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Lugar> getEntityClass() {
        return Lugar.class;
    }
}
