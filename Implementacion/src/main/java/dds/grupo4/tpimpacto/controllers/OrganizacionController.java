package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.AceptarSolicitudRequest;
import dds.grupo4.tpimpacto.dtos.CrearOrganizacionRequest;
import dds.grupo4.tpimpacto.dtos.ListarMiembrosResponse;
import dds.grupo4.tpimpacto.dtos.ListarOrganizacionesResponse;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
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
    public ResponseEntity<ListarOrganizacionesResponse> listarOrganizaciones() {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarOrganizaciones());
    }

    @PostMapping("/aceptar-solicitud")
    public ResponseEntity<BaseResponse> aceptarSolicitud(@RequestBody AceptarSolicitudRequest request) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.aceptarSolicitud(request));
    }

    @GetMapping("/miembros/{id}")
    public ResponseEntity<ListarMiembrosResponse> listarMiembros(@PathVariable long id) {
        return ResponseEntityUtils.toResponseEntity(organizacionService.listarMiembros(id));
    }

    public void cargarMediciones() throws IOException {
        // TODO: ver como recibir archivos (creo que es algo de MultiPart)
        /*
        Organizacion organizacion = buscarOrganizacionPorRazonSocial();


        URL resource = getClass().getClassLoader().getResource(pathPrueba);
        String filePath = resource.getPath();
        File file = new File(filePath);

        MedicionesDataLoader dataLoader = new MedicionesDataLoader();
        List<RowMedicionActividad> mediciones = dataLoader.loadData(file);
        organizacionService.cargarMediciones(organizacion, mediciones);
        */
    }

}
