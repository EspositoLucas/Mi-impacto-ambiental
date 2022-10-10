package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.MedioDeTransporteDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.services.MedioDeTransporteService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medioDeTransporte")
public class MedioDeTransporteController {

    private final MedioDeTransporteService medioDeTransporteService;

    public MedioDeTransporteController(MedioDeTransporteService medioDeTransporteService) {
        this.medioDeTransporteService = medioDeTransporteService;
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<MedioDeTransporteDto>> listarMediosDeTransporte() {
        return ResponseEntityUtils.toResponseEntity(medioDeTransporteService.listarMediosDeTransporte());
    }
}
