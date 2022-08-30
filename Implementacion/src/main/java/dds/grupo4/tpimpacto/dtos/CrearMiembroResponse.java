package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CrearMiembroResponse extends BaseResponse {
    private Long idSolicitud;

    public CrearMiembroResponse(HttpStatus status, String message, Long idSolicitud) {
        super(status, message);
        this.idSolicitud = idSolicitud;
    }
}
