package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.CrearPersonaRequest;
import dds.grupo4.tpimpacto.dtos.PersonaDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.services.PersonaService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crearPersona(@RequestBody CrearPersonaRequest request) {
        return ResponseEntityUtils.toResponseEntity(personaService.crearPersona(request));
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<PersonaDto>> listarPersonas() {
        return ResponseEntityUtils.toResponseEntity(personaService.listarPersonas());
    }
}
