package dds.grupo4.tpimpacto.services;

import java.util.Optional;

import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;

public interface TipoConsumoService extends BaseService<TipoConsumo> {
    Optional<TipoConsumo> getByNombre(String nombreTipo);
}
