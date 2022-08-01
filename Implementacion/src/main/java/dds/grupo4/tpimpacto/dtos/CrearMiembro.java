package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CrearMiembro {

    private CrearMiembro() {
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private int idPersona;
        private String username;
        private String password;
        private int idOrganizacion;
        private int idSector;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private int idSolicitud;
    }
}
