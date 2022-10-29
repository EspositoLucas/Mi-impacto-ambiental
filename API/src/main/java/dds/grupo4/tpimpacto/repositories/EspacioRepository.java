package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class EspacioRepository extends BaseRepository<Espacio> {

    public EspacioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Espacio> getEntityClass() {
        return Espacio.class;
    }
}
