package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.CrearMiembroRequest;
import dds.grupo4.tpimpacto.dtos.CrearMiembroResponse;
import dds.grupo4.tpimpacto.services.MiembroService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/miembro")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @PostMapping
    public ResponseEntity<CrearMiembroResponse> crearMiembro(@RequestBody CrearMiembroRequest request) {
        return ResponseEntityUtils.toResponseEntity(miembroService.crearMiembro(request));
    }
}
