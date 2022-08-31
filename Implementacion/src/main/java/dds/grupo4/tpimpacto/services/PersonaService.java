package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.CrearPersonaRequest;
import dds.grupo4.tpimpacto.dtos.PersonaDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import dds.grupo4.tpimpacto.entities.organizacion.TipoDocumento;
import dds.grupo4.tpimpacto.repositories.PersonaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService extends BaseService<Persona, PersonaRepository> {

    public PersonaService(PersonaRepository repository) {
        super(repository);
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

}
