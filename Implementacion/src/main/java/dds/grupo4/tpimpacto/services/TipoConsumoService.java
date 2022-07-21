package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;

import java.util.Optional;

public interface TipoConsumoService extends BaseService<TipoConsumo> {
    Optional<TipoConsumo> getByNombre(String nombreTipo);
}
