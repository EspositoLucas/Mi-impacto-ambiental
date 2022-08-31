package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearSectorRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.repositories.EspacioRepository;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SectorServiceImpl extends BaseServiceImpl<Sector, SectorRepository> implements SectorService {

    private final OrganizacionRepository organizacionRepository;
    private final EspacioRepository espacioRepository;

    public SectorServiceImpl(SectorRepository sectorRepository, OrganizacionRepository organizacionRepository, EspacioRepository espacioRepository) {
        super(sectorRepository);
        this.organizacionRepository = organizacionRepository;
        this.espacioRepository = espacioRepository;
    }

    @Override
    @Transactional
    public BaseResponse crearSector(CrearSectorRequest request) {
        Organizacion organizacion = organizacionRepository.getById(request.getIdOrganizacion());
        if (organizacion == null) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "No existe la Organizacion con el ID especificado");
        }

        Espacio espacio = espacioRepository.getById(request.getIdEspacio());
        if (espacio == null) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "No existe el Espacio con el ID especificado");
        }

        Sector nuevoSector = new Sector(request.getNombre(), organizacion, espacio);
        this.save(nuevoSector);
        return new BaseResponse(HttpStatus.CREATED);
    }
}
