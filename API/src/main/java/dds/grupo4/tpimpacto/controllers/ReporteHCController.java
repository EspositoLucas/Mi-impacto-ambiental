package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.ClasificacionConHC;
import dds.grupo4.tpimpacto.dtos.OrganizacionConHC;
import dds.grupo4.tpimpacto.dtos.SectorTerritorialConHC;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.services.SectorTerritorialService;
import dds.grupo4.tpimpacto.services.calculohc.CalculadoraHC;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportehc")
public class ReporteHCController {

    private final CalculadoraHC calculadoraHC;
    private final SectorTerritorialService sectorTerritorialService;

    public ReporteHCController(CalculadoraHC calculadoraHC, SectorTerritorialService sectorTerritorialService) {
        this.calculadoraHC = calculadoraHC;
        this.sectorTerritorialService = sectorTerritorialService;
    }

    @GetMapping("/hc-por-sector-territorial")
    public ResponseEntity<ResponseWithResults<SectorTerritorialConHC>> hcPorSectorTerritorial() {
        List<SectorTerritorial> sectoresTerritoriales = sectorTerritorialService.getAll();
        List<SectorTerritorialConHC> sectoresTerritorialesConHCs = sectoresTerritoriales.stream()
                .map(sectorTerritorial -> {
                    Cantidad hc = calculadoraHC.hcTotalSectorTerritorial(sectorTerritorial);
                    return SectorTerritorialConHC.from(sectorTerritorial, hc);
                })
                .collect(Collectors.toList());
        return ResponseEntityUtils.toResponseEntity(new ResponseWithResults<>(HttpStatus.OK, sectoresTerritorialesConHCs));
    }

    @GetMapping("/composicion-de-sector-territorial/{id}")
    public ResponseEntity<ResponseWithResults<OrganizacionConHC>> composicionDeSectorTerritorial(@PathVariable long idSectorTerritorial) {
        SectorTerritorial sectorTerritorial = sectorTerritorialService.getById(idSectorTerritorial);
        Map<Organizacion, Cantidad> hcsPorOrganizacion = calculadoraHC.hcTotalSectorTerritorialSeparadoPorOrganizacion(sectorTerritorial);
        List<OrganizacionConHC> organizacionesConHCs = hcsPorOrganizacion.entrySet().stream()
                .map(entry -> OrganizacionConHC.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return ResponseEntityUtils.toResponseEntity(new ResponseWithResults<>(HttpStatus.OK, organizacionesConHCs));
    }

    @GetMapping("/hc-por-clasificacion-organizacion")
    public ResponseEntity<ResponseWithResults<ClasificacionConHC>> hcPorClasificacionOrganizacion() {
        Map<Clasificacion, Cantidad> hcsPorClasificacion = calculadoraHC.hcTotalSeparadoPorClasificacionDeOrganizacion();
        List<ClasificacionConHC> clasificacionesConHCs = hcsPorClasificacion.entrySet().stream()
                .map(entry -> ClasificacionConHC.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return ResponseEntityUtils.toResponseEntity(new ResponseWithResults<>(HttpStatus.OK, clasificacionesConHCs));
    }

}
