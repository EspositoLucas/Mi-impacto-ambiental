package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MiembroRepository extends BaseRepository<Miembro> {

    public MiembroRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Miembro> getEntityClass() {
        return Miembro.class;
    }
}
