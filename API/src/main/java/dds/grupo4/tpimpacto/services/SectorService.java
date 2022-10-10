package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearSectorRequest;
import dds.grupo4.tpimpacto.dtos.SectorDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.entities.trayecto.TipoEspacio;
import dds.grupo4.tpimpacto.repositories.EspacioRepository;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService extends BaseService<Sector, SectorRepository> {

    private final OrganizacionRepository organizacionRepository;
    private final EspacioRepository espacioRepository;
    private final DireccionService direccionService;

    public SectorService(SectorRepository sectorRepository, OrganizacionRepository organizacionRepository, EspacioRepository espacioRepository, DireccionService direccionService) {
        super(sectorRepository);
        this.organizacionRepository = organizacionRepository;
        this.espacioRepository = espacioRepository;
        this.direccionService = direccionService;
    }

    @Transactional
    public BaseResponse crearSector(CrearSectorRequest request) {
        Organizacion organizacion = organizacionRepository.getById(request.getIdOrganizacion());
        if (organizacion == null) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "No existe la Organizacion con el ID especificado");
        }

        Espacio espacio;
        if (request.getEspacio().getId() != null && request.getEspacio().getId() != 0) {
            espacio = espacioRepository.getById(request.getEspacio().getId());
            if (espacio == null) {
                return new BaseResponse(HttpStatus.BAD_REQUEST, "No existe el Espacio con el ID especificado");
            }
        } else {
            Direccion direccion = direccionService.getOrCreateDireccion(request.getEspacio().getDireccion());
            espacio = new Espacio(direccion, request.getEspacio().getNombre(), TipoEspacio.valueOf(request.getEspacio().getTipoEspacio()));
            espacioRepository.save(espacio);
        }

        Sector nuevoSector = new Sector(request.getNombre(), organizacion, espacio);
        this.save(nuevoSector);
        return new BaseResponse(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseWithResults<SectorDto> listarSectores() {
        List<Sector> sectores = this.getAll();
        List<SectorDto> sectoresDtos = sectores.stream()
                .map(SectorDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, sectoresDtos);
    }
}
