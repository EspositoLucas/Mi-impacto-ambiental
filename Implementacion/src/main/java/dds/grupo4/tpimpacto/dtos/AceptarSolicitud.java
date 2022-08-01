package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AceptarSolicitud {

    private AceptarSolicitud() {
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private int idSolicitud;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
    }

}
