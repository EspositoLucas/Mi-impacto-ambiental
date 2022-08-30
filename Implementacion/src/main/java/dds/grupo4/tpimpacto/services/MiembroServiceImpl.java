package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearMiembroRequest;
import dds.grupo4.tpimpacto.dtos.CrearMiembroResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import dds.grupo4.tpimpacto.repositories.PersonaRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MiembroServiceImpl extends BaseServiceImpl<Miembro, MiembroRepository> implements MiembroService {

    private final PersonaRepository personaRepository;
    private final SolicitudRepository solicitudRepository;
    private final SectorRepository sectorRepository;

    public MiembroServiceImpl(MiembroRepository miembroRepository, PersonaRepository personaRepository, SolicitudRepository solicitudRepository, SectorRepository sectorRepository) {
        super(miembroRepository);
        this.personaRepository = personaRepository;
        this.solicitudRepository = solicitudRepository;
        this.sectorRepository = sectorRepository;
    }

    @Override
    @Transactional
    public CrearMiembroResponse crearMiembro(CrearMiembroRequest request) {
        Persona persona = personaRepository.getById(request.getIdPersona());
        if (persona == null) {
            return new CrearMiembroResponse(HttpStatus.BAD_REQUEST, "No existe ninguna Persona con el ID especificado", null);
        }

        Sector sector = sectorRepository.getById(request.getIdSector());
        if (sector == null || sector.getOrganizacion().getId() != request.getIdOrganizacion()) {
            throw new IllegalArgumentException(
                    "El sector no se encontro, o no pertenece a la organizacion especificada"
            );
        }

        Miembro miembro = new Miembro(persona);
        this.save(miembro);
        Solicitud solicitud = new Solicitud(miembro, sector, sector.getOrganizacion());
        solicitudRepository.save(solicitud);

        log.debug("Solicitud con ID " + solicitud.getId() +
                " creada para vincular al nuevo miembro al sector de ID " + sector.getId());

        return new CrearMiembroResponse(HttpStatus.CREATED, "OK", solicitud.getId());
    }
}
