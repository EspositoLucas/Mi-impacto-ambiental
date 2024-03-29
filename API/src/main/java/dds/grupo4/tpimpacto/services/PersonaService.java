package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearPersonaRequest;
import dds.grupo4.tpimpacto.dtos.IdTextPair;
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
import dds.grupo4.tpimpacto.services.base.BaseService;
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
        TipoDocumento tipoDocumento = TipoDocumento.getFromOrdinal(request.getTipoDocumento().getId());
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
    public ResponseWithResults<IdTextPair> listarPersonasIdTextPair() {
        List<IdTextPair> idTextPairs = this.getAll().stream()
                .map(IdTextPair::new)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, idTextPairs);
    }

    @Transactional
    public ResponseWithResults<IdTextPair> listarTiposDeDocumento() {
        List<IdTextPair> tiposDeDocumento = Arrays.stream(TipoDocumento.values())
                .map(tipo -> new IdTextPair(tipo.ordinal(), tipo.toString()))
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, tiposDeDocumento);
    }

    @Transactional
    public void seedData() {
        if (this.hasData()) {
            log.debug("Seed: ya hay Personas creadas");
            return;
        }

        log.debug("Seed: se crean las Personas iniciales (con miembro y usuario)");

        Persona personaEchi = new Persona("Ezequiel", "Saidman", TipoDocumento.DNI, "12345678");
        Miembro miembroEchi = new Miembro(personaEchi);
        personaEchi.addMiembro(miembroEchi);

        Persona personaMili = new Persona("Milagros", "Ramos", TipoDocumento.DNI, "87654321");
        Miembro miembroMili = new Miembro(personaMili);
        personaMili.addMiembro(miembroMili);

        Persona personaLucas = new Persona("Lucas", "Esposito", TipoDocumento.DNI, "11223344");
        Miembro miembroLucas = new Miembro(personaLucas);
        personaLucas.addMiembro(miembroLucas);

        Sector sector = sectorRepository.getByNombre("Sector TEST").get();
        sector.addMiembro(miembroEchi);
        miembroEchi.setFechaIngreso(LocalDate.now());
        sector.addMiembro(miembroMili);
        miembroMili.setFechaIngreso(LocalDate.now());
        sector.addMiembro(miembroLucas);
        miembroLucas.setFechaIngreso(LocalDate.now());

        Usuario usuarioEchi = usuarioService.crearUsuario("echisaidman", "echisaidman", true);
        miembroEchi.setUsuario(usuarioEchi);

        Usuario usuarioMili = usuarioService.crearUsuario("miliramos", "miliramos", true);
        miembroMili.setUsuario(usuarioMili);

        Usuario usuarioLucas = usuarioService.crearUsuario("lucasesposito", "lucasesposito", false);
        miembroLucas.setUsuario(usuarioLucas);

        this.saveAll(Arrays.asList(personaEchi, personaMili, personaLucas));
    }

}
