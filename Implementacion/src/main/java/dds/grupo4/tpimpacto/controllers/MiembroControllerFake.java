package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.entities.organizacion.TipoDocumento;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.entities.seguridad.Persona;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.services.MiembroService;
import dds.grupo4.tpimpacto.services.OrganizacionService;
import dds.grupo4.tpimpacto.services.SectorService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MiembroControllerFake {

    private final MiembroService miembroService;
    private final OrganizacionService organizacionService;
    private final SectorService sectorService;

    public MiembroControllerFake(MiembroService miembroService, OrganizacionService organizacionService, SectorService sectorService) {
        this.miembroService = miembroService;
        this.organizacionService = organizacionService;
        this.sectorService = sectorService;
    }

    public void crearMiembro() {
        ConsoleHelper.print("Nombre del miembro: ");
        String nombreMiembro = ConsoleHelper.readString();
        ConsoleHelper.print("DNI del miembro: ");
        String dniMiembro = ConsoleHelper.readString();

        ConsoleHelper.print("Organizacion donde trabaja el miembro: ");
        String razonSocialOrganizacion = ConsoleHelper.readString();
        ConsoleHelper.print("Sector de la organizacion donde trabaja el miembro: ");
        String nombreSector = ConsoleHelper.readString();

        Optional<Sector> optionalSector = sectorService.getByNombreYOrganizacion(nombreSector, razonSocialOrganizacion);
        while (!optionalSector.isPresent()) {
            ConsoleHelper.printLine("ERROR: el sector especificado no fue encontrado");

            ConsoleHelper.print("Organizacion donde trabaja el miembro: ");
            razonSocialOrganizacion = ConsoleHelper.readString();
            ConsoleHelper.print("Sector de la organizacion donde trabaja el miembro: ");
            nombreSector = ConsoleHelper.readString();
            optionalSector = sectorService.getByNombreYOrganizacion(nombreSector, razonSocialOrganizacion);
        }

        Sector sector = optionalSector.get();

        // Para las pruebas creamos una Persona para cada Miembro, aunque en realidad cada
        // Persona puede tener muchos Miembros
        Persona nuevaPersona = new Persona(nombreMiembro, "", TipoDocumento.DNI, dniMiembro);

        Usuario nuevoUsuario = new Usuario(nombreMiembro, nombreMiembro);

        Miembro nuevoMiembro = new Miembro(nuevaPersona, nuevoUsuario, sector);
        miembroService.save(nuevoMiembro);

        Solicitud solicitud = new Solicitud(nuevoMiembro, sector);
        Organizacion organizacion = sector.getOrganizacion();
        organizacionService.agregarSolicitud(organizacion, solicitud);
    }

}
