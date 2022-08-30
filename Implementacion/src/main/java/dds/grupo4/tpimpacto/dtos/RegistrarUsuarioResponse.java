package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class RegistrarUsuarioResponse extends BaseResponse {

    private List<String> errores;

    public RegistrarUsuarioResponse(HttpStatus status, String message) {
        super(status, message);
    }

    public RegistrarUsuarioResponse(HttpStatus status, String message, List<String> errores) {
        this(status, message);
        this.errores = errores;
    }
}
