package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearMiembro;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;

public interface MiembroService extends BaseService<Miembro> {
    CrearMiembro.Response crearMiembro(CrearMiembro.Request request);
}
