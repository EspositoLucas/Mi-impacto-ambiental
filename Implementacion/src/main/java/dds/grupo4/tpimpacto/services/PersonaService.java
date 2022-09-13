package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearPersonaRequest;
import dds.grupo4.tpimpacto.dtos.PersonaDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.TipoDocumento;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.PersonaRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonaService extends BaseService<Persona, PersonaRepository> {

    private final SectorRepository sectorRepository;
    private final UsuarioService usuarioService;

    public PersonaService(PersonaRepository repository, SectorRepository sectorRepository, UsuarioService usuarioService) {
        super(repository);
        this.sectorRepository = sectorRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public BaseResponse crearPersona(CrearPersonaRequest request) {
        TipoDocumento tipoDocumento = TipoDocumento.valueOf(request.getTipoDocumento());
        Persona persona = new Persona(request.getNombre(), request.getApellido(), tipoDocumento, request.getDocumento());
        this.save(persona);
        return new BaseResponse(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseWithResults<PersonaDto> listarPersonas() {
        List<PersonaDto> dtos = this.getAll().stream()
                .map(PersonaDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Seed: ya hay Personas creadas");
            return;
        }

        log.debug("Seed: se crean las Personas iniciales (con miembro y usuario)");

        Persona persona = new Persona("Ezequiel", "Saidman", TipoDocumento.DNI, "12345678");
        Miembro miembro = new Miembro(persona);
        persona.addMiembro(miembro);

        Sector sector = sectorRepository.getByNombre("Sector TEST").get();
        sector.addMiembro(miembro);
        miembro.setFechaIngreso(LocalDate.now());
        Usuario usuario = usuarioService.crearUsuario("echisaidman", "echisaidman", true);
        miembro.setUsuario(usuario);
        this.saveAll(Arrays.asList(persona));
    }

}
