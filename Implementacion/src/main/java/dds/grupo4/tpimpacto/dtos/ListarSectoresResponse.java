package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListarSectoresResponse extends BaseResponse {
    private List<SectorDto> sectores;

    public ListarSectoresResponse(HttpStatus status, List<SectorDto> sectores) {
        super(status);
        this.sectores = sectores;
    }
}
