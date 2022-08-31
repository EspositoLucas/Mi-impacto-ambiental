package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ListarMiembrosResponse extends BaseResponse {
    private List<MiembroDto> miembros;

    public ListarMiembrosResponse(HttpStatus status, List<MiembroDto> miembros) {
        super(status);
        this.miembros = miembros;
    }
}
