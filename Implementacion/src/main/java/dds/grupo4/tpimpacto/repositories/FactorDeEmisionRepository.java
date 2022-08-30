package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioTransporte;

import java.util.Optional;

public interface FactorDeEmisionRepository extends BaseRepository<FactorDeEmision> {

    Optional<FactorDeEmision> getByTipoDeTransporte(TipoMedioTransporte tipo);
}
