package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.entities.trayecto.TipoEspacio;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import dds.grupo4.tpimpacto.services.SectorService;

import java.util.NoSuchElementException;
import java.util.Optional;

public class SectorControllerFake {

    private final SectorService sectorService;
    private final OrganizacionService organizacionService;

    public SectorControllerFake(SectorService sectorService, OrganizacionService organizacionService) {
        this.sectorService = sectorService;
        this.organizacionService = organizacionService;
    }

    public void crearSector() {
        ConsoleHelper.print("Nombre del sector: ");
        String nombreSector = ConsoleHelper.readString();
        Organizacion organizacion = buscarOrganizacionPorRazonSocial();
        Espacio espacio = new Espacio(null, "Espacio de prueba", TipoEspacio.TRABAJO);

        Sector nuevoSector = new Sector(nombreSector, organizacion, espacio);
        sectorService.save(nuevoSector);
    }

    private Organizacion buscarOrganizacionPorRazonSocial() throws NoSuchElementException {
        ConsoleHelper.print("Razon social de la Organizacion: ");
        String razonSocialOrganizacion = ConsoleHelper.readString();

        Optional<Organizacion> optionalOrganizacion = organizacionService.getByRazonSocial(razonSocialOrganizacion);
        return optionalOrganizacion.get();
    }

}

