package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.CrearMiembro;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.services.MiembroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MiembroControllerFake {

    private final MiembroService miembroService;

    public MiembroControllerFake(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    public void crearMiembro() {
        int idPersona = ConsoleHelper.readInt("ID de la persona: ");

        String username = ConsoleHelper.readString("Username del miembro: ");
        String password = ConsoleHelper.readString("Password del miembro: ");

        int idOrganizacion = ConsoleHelper.readInt("ID de la organizacion del miembro: ");
        int idSector = ConsoleHelper.readInt("ID del sector del miembro: ");

        CrearMiembro.Request request = new CrearMiembro.Request(idPersona, username, password, idOrganizacion, idSector);
        CrearMiembro.Response response = miembroService.crearMiembro(request);
        log.debug("Solicitud con ID " + response.getIdSolicitud() +
                " creada para vincular al nuevo miembro al sector de ID " + idSector);
    }

}
