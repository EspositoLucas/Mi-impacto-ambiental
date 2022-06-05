package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.cargamediciones.MedicionesDataLoader;
import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.Espacio;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.entities.Sector;
import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.services.OrganizacionService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class OrganizacionControllerFake {

    private final OrganizacionService organizacionService;

    public OrganizacionControllerFake(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    public void crearOrganizacion() {
        ConsoleHelper.print("Razon social de organizacion: ");
        String razonSocial = ConsoleHelper.readString();

        // Para simplificar, usamos estos valores predeterminados
        TipoOrganizacion tipoOrganizacion = TipoOrganizacion.EMPRESA;
        Clasificacion clasificacion = Clasificacion.EMPRESA_SECTOR_PRIMARIO;

        Organizacion nuevaOrganizacion = new Organizacion(razonSocial, tipoOrganizacion, clasificacion);

        String nombreSector = "";
        while (!nombreSector.equals("FIN")) {
            ConsoleHelper.print("Sector para la organizacion " + razonSocial + " (FIN para finalizar): ");
            nombreSector = ConsoleHelper.readString();
            if (!nombreSector.equals("FIN")) {
                Espacio espacioSector = null;
                Sector nuevoSector = new Sector(nombreSector, espacioSector);
                nuevaOrganizacion.addSector(nuevoSector);
            }
        }

        organizacionService.save(nuevaOrganizacion);
    }

    public void listarOrganizaciones() {
        List<Organizacion> organizaciones = organizacionService.getAll();
        for (Organizacion organizacion : organizaciones) {
            String message = "";
            message += "Organizacion " + organizacion.getRazonSocial() + " con sectores:";
            for (Sector sector : organizacion.getSectores()) {
                message += "\n - " + sector.getNombre();
            }
            ConsoleHelper.printLine(message);
        }
    }

    public void cargarMediciones() throws IOException {
        String pathPrueba = "static/PruebaCargaMediciones.xlsx";
        URL resource = getClass().getClassLoader().getResource(pathPrueba);
        String filePath = resource.getPath();
        File file = new File(filePath);

        MedicionesDataLoader dataLoader = new MedicionesDataLoader();
        List<RowMedicionActividad> mediciones = dataLoader.loadData(file);
        organizacionService.cargarMediciones(mediciones);
    }

}
