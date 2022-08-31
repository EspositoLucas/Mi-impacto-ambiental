package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearTipoServicioContratadoRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoServicioContratado;

public interface TipoServicioContratadoService extends BaseService<TipoServicioContratado> {
    BaseResponse crearTipoServicioContratado(CrearTipoServicioContratadoRequest request);
}
