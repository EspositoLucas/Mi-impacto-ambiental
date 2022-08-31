package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearSectorRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;

public interface SectorService extends BaseService<Sector> {
    BaseResponse crearSector(CrearSectorRequest request);
}
