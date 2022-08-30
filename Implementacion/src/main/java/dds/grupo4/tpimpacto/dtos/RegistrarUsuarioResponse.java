package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistrarUsuarioResponse extends BaseResponse {

    private List<String> errores;

    public RegistrarUsuarioResponse(String message) {
        super(message);
    }

    public RegistrarUsuarioResponse(String message, List<String> errores) {
        this(message);
        this.errores = errores;
    }
}
