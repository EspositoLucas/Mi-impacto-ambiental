package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearMiembroRequest;
import dds.grupo4.tpimpacto.dtos.CrearMiembroResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;

public interface MiembroService extends BaseService<Miembro> {
    CrearMiembroResponse crearMiembro(CrearMiembroRequest request);
}
