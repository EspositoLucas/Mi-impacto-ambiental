package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.CrearTransportePublicoRequest;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.security.RequireAdminRole;
import dds.grupo4.tpimpacto.services.TransportePublicoService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportepublico")
public class TransportePublicoController {

    private final TransportePublicoService transportePublicoService;

    public TransportePublicoController(TransportePublicoService transportePublicoService) {
        this.transportePublicoService = transportePublicoService;
    }

    @RequireAdminRole
    @PostMapping
    public ResponseEntity<BaseResponse> crearTransportePublico(@RequestBody CrearTransportePublicoRequest request) {
        return ResponseEntityUtils.toResponseEntity(transportePublicoService.crearTransportePublico(request));
    }

}
