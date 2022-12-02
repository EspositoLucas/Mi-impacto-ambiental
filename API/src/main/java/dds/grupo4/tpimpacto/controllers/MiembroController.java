package dds.grupo4.tpimpacto.controllers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import dds.grupo4.tpimpacto.dtos.CrearMiembroRequest;
import dds.grupo4.tpimpacto.dtos.CrearMiembroResponse;
import dds.grupo4.tpimpacto.dtos.MiembroDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.security.RequireAdminRole;
import dds.grupo4.tpimpacto.services.MiembroService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/miembro")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @RequireAdminRole
    @PostMapping
    public ResponseEntity<CrearMiembroResponse> crearMiembro(@RequestBody CrearMiembroRequest request) {
        return ResponseEntityUtils.toResponseEntity(miembroService.crearMiembro(request));
    }

    //Cliente pesado
    @GetMapping
    public ResponseEntity<ResponseWithResults<MiembroDto>> listarMiembros() {
        return ResponseEntityUtils.toResponseEntity(miembroService.listarMiembros());
    }







}
