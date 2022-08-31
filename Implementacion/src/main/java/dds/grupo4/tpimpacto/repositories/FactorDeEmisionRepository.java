package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioTransporte;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class FactorDeEmisionRepository extends BaseRepository<FactorDeEmision> {

    public FactorDeEmisionRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<FactorDeEmision> getEntityClass() {
        return FactorDeEmision.class;
    }

    public Optional<FactorDeEmision> getByTipoDeTransporte(TipoMedioTransporte tipo) {
        String query = "FROM FactorDeEmision factorDeEmision " +
                "WHERE factorDeEmision.tipoMedioDeTransporte = :tipo";
        return entityManager.createQuery(query, FactorDeEmision.class)
                .setParameter("tipo_medio_transporte", tipo)
                .getResultStream()
                .findFirst();
    }
}
