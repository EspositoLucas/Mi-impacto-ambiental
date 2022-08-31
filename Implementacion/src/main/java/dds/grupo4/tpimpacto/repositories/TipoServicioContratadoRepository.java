package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;

import java.util.Optional;

public interface TipoServicioContratadoRepository extends BaseRepository<TipoServicioContratado> {
    Optional<TipoServicioContratado> getByNombre(String nombre);
}
