package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.DireccionDto;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;

public interface DireccionService extends BaseService<Direccion> {
    Direccion getOrCreateDireccion(DireccionDto direccionDto);
}
