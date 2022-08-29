package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioTransporte;

import javax.persistence.EntityManager;

import java.util.Optional;

public class FactorDeEmisionRepositoryImpl extends BaseRepositoryImpl<FactorDeEmision> implements FactorDeEmisionRepository{

    public FactorDeEmisionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<FactorDeEmision> getEntityClass() {
        return FactorDeEmision.class;
    }

    @Override
    public Optional<FactorDeEmision> getByTipoDeTransporte(TipoMedioTransporte tipo) {
        String query = "FROM FactorDeEmision factorDeEmision " +
                "WHERE factorDeEmision.tipoMedioDeTransporte = :tipo";
        return entityManager.createQuery(query, FactorDeEmision.class)
                .setParameter("tipo_medio_transporte", tipo)
                .getResultStream()
                .findFirst();
    }
}
