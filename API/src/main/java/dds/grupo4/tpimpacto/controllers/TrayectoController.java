package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.TrayectoDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.services.TrayectoService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trayecto")
public class TrayectoController {

    private final TrayectoService trayectoService;

    public TrayectoController(TrayectoService trayectoService) {
        this.trayectoService = trayectoService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crearTrayecto(@RequestBody TrayectoDto trayectoDto) {
        trayectoService.crearTrayecto(trayectoDto);
        return ResponseEntityUtils.toResponseEntity(new BaseResponse(HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<TrayectoDto>> listarTrayectos() {
        return ResponseEntityUtils.toResponseEntity(trayectoService.listarTrayectos());
    }
}
