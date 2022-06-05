package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.cargamediciones.MedicionesDataLoader;
import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.services.OrganizacionService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OrganizacionController {

    private final OrganizacionService organizacionService;

    public OrganizacionController(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    public void cargarMediciones() throws IOException {
        String path = "static/PruebaCargaMediciones.xlsx";
        URL resource = getClass().getClassLoader().getResource(path);
        String filePath = resource.getPath();
        File file = new File(filePath);

        MedicionesDataLoader dataLoader = new MedicionesDataLoader();
        List<RowMedicionActividad> mediciones = dataLoader.loadData(file);
        organizacionService.cargarMediciones(mediciones);
    }

}
