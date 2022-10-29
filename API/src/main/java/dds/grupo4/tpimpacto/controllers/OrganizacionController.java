package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.*;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithSingleResult;
import dds.grupo4.tpimpacto.security.RequireAdminRole;
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

    @GetMapping
    public ResponseEntity<ResponseWithResults<OrganizacionDto>> listarOrganizaciones() {
        return ResponseEntityUtils.toResponseEntity(organizacionService.getAllDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithSingleResult<OrganizacionDto>> getOrganizacion(@PathVariable long id) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.getDtoById(id));
    }

    @RequireAdminRole
    @PostMapping
    public ResponseEntity<BaseResponse> crearOrganizacion(@RequestBody OrganizacionDto request) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.saveFromDto(request));
    }

    @RequireAdminRole
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> editarOrganizacion(@PathVariable long id, @RequestBody OrganizacionDto request) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.editFromDto(id, request));
    }

    @RequireAdminRole
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteOrganizacion(@PathVariable long id) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.deleteEntity(id));
    }

    @GetMapping("/tipos")
    public ResponseEntity<ResponseWithResults<IdTextPair>> listarTiposDeOrganizacion() {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarTiposDeOrganizacion());
    }

    @GetMapping("/clasificaciones")
    public ResponseEntity<ResponseWithResults<IdTextPair>> listarClasificaciones() {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarClasificaciones());
    }

    @RequireAdminRole
    @PostMapping("/aceptar-solicitud")
    public ResponseEntity<BaseResponse> aceptarSolicitud(@RequestBody AceptarSolicitudRequest request) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.aceptarSolicitud(request));
    }

    @GetMapping("/miembros/{id}")
    public ResponseEntity<ResponseWithResults<MiembroDto>> listarMiembros(@PathVariable long id) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarMiembros(id));
    }

    @RequireAdminRole
    @PostMapping("/cargar-mediciones")
    public ResponseEntity<BaseResponse> cargarMediciones(CargarMedicionesRequest request) throws IOException {
        organizacionService.cargarMediciones(request);
        return ResponseEntityUtils.toResponseEntity(new BaseResponse(HttpStatus.OK));
    }

}
