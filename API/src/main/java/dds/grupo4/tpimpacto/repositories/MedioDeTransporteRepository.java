package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MedioDeTransporteRepository extends BaseRepository<MedioDeTransporte> {

    public MedioDeTransporteRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<MedioDeTransporte> getEntityClass() {
        return MedioDeTransporte.class;
    }
}
