package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.CrearSectorRequest;
import dds.grupo4.tpimpacto.dtos.SectorDto;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.services.SectorService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sector")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> crearSector(@RequestBody CrearSectorRequest request) {
        return ResponseEntityUtils.toResponseEntity(sectorService.crearSector(request));
    }

    @GetMapping
    public ResponseEntity<ResponseWithResults<SectorDto>> listarSectores() {
        return ResponseEntityUtils.toResponseEntity(sectorService.listarSectores());
    }

}