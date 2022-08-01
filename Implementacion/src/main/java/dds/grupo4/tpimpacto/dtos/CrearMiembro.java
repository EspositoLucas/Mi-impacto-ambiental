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
        private long idPersona;
        private String username;
        private String password;
        private long idOrganizacion;
        private long idSector;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long idSolicitud;
    }
}
