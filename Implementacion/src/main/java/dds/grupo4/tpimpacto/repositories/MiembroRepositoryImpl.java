package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MiembroRepositoryImpl extends BaseRepositoryImpl<Miembro> implements MiembroRepository {

    public MiembroRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Miembro> getEntityClass() {
        return Miembro.class;
    }
}
