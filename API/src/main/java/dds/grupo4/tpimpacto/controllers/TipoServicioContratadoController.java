package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.CrearTipoServicioContratadoRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.security.RequireAdminRole;
import dds.grupo4.tpimpacto.services.TipoServicioContratadoService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tiposerviciocontratado")
public class TipoServicioContratadoController {

    private final TipoServicioContratadoService tipoServicioContratadoService;


    public TipoServicioContratadoController(TipoServicioContratadoService tipoServicioContratadoService) {
        this.tipoServicioContratadoService = tipoServicioContratadoService;
    }

    @RequireAdminRole
    @PostMapping
    public ResponseEntity<BaseResponse> crearTipoServicioContratado(@RequestBody CrearTipoServicioContratadoRequest request) {
        return ResponseEntityUtils.toResponseEntity(tipoServicioContratadoService.crearTipoServicioContratado(request));
    }
}
