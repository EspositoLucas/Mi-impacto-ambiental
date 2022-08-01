package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearMiembro;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import dds.grupo4.tpimpacto.repositories.PersonaRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
    public CrearMiembro.Response crearMiembro(CrearMiembro.Request request) {
        Persona persona = personaRepository.getById(request.getIdPersona());
        Usuario usuario = new Usuario(request.getUsername(), request.getPassword());
        Miembro miembro = new Miembro(persona, usuario);

        Sector sector = sectorRepository.getById(request.getIdSector());
        if (sector.getOrganizacion().getId() != request.getIdOrganizacion()) {
            throw new IllegalArgumentException(
                    "El sector de ID " + sector.getId() + " no pertenece a la organizacion de ID " + request.getIdOrganizacion()
            );
        }

        Solicitud solicitud = new Solicitud(miembro, sector, sector.getOrganizacion());
        solicitudRepository.save(solicitud);

        return new CrearMiembro.Response(solicitud.getId());
    }
}
