package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.*;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/organizacion")
public class OrganizacionController {

    private final OrganizacionService organizacionService;

    public OrganizacionController(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crearOrganizacion(@RequestBody CrearOrganizacionRequest request) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.crearOrganizacion(request));
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<OrganizacionDto>> listarOrganizaciones() {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarOrganizaciones());
    }

    @PostMapping("/aceptar-solicitud")
    public ResponseEntity<BaseResponse> aceptarSolicitud(@RequestBody AceptarSolicitudRequest request) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.aceptarSolicitud(request));
    }

    @GetMapping("/miembros/{id}")
    public ResponseEntity<ResponseWithResults<MiembroDto>> listarMiembros(@PathVariable long id) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarMiembros(id));
    }

    @PostMapping("/cargar-mediciones")
    public ResponseEntity<BaseResponse> cargarMediciones(CargarMedicionesRequest request) throws IOException {
        organizacionService.cargarMediciones(request);
        return ResponseEntityUtils.toResponseEntity(new BaseResponse(HttpStatus.OK));
    }

}
