package dds.grupo4.tpimpacto.controllers;

import com.sun.istack.NotNull;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
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
    private Direccion direccionEspacio;
    private TipoEspacio tipoEspacio;

    public SectorControllerFake(SectorService sectorService, OrganizacionService organizacionService) {
        this.sectorService = sectorService;
        this.organizacionService = organizacionService;
    }


    public void crearSector() {
        ConsoleHelper.print("Nombre del sector: ");
        String nombreSector = ConsoleHelper.readString();
        Organizacion organizacion = buscarOrganizacionPorRazonSocial();
        ConsoleHelper.print("Nombre del espacio donde esta el sector: ");
        String nombreEspacio = ConsoleHelper.readString();
        Espacio espacio = definirEspacio(direccionEspacio, nombreEspacio, tipoEspacio);

        Sector nuevoSector = new Sector(nombreSector, organizacion, espacio);

        try {

            ConsoleHelper.printLine("Miembros de sector " + nuevoSector.getNombre() + ":");
            for (Miembro miembro : nuevoSector.getMiembros()) {
                String message = "\n - " + miembro.getPersona().getNombre() + " (" + miembro.getDocumento() + ")";
                ConsoleHelper.printLine(message);
            }
        } catch (NoSuchElementException e) {
            ConsoleHelper.printLine(e.getMessage());
        }

        sectorService.save(nuevoSector);
    }

    public SectorControllerFake definirDireccion(@NotNull Direccion direccion) {
        this.direccionEspacio = direccion;
        return this;
    }

    public SectorControllerFake definirTipoEspacio(@NotNull TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
        return this;
    }

    private Organizacion buscarOrganizacionPorRazonSocial() throws NoSuchElementException {
        ConsoleHelper.print("Razon social de la Organizacion: ");
        String razonSocialOrganizacion = ConsoleHelper.readString();

        Optional<Organizacion> optionalOrganizacion = organizacionService.getByRazonSocial(razonSocialOrganizacion);
        return optionalOrganizacion.get();
    }

    public Espacio definirEspacio(Direccion direccion, String nombre, TipoEspacio tipoEspacio) {
        new Espacio(direccion, nombre, tipoEspacio);
    }

}

