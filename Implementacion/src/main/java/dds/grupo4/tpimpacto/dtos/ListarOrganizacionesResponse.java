package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ListarOrganizacionesResponse extends BaseResponse {
    private List<OrganizacionDto> organizaciones;

    public ListarOrganizacionesResponse(HttpStatus status, List<OrganizacionDto> organizaciones) {
        super(status);
        this.organizaciones = organizaciones;
    }
}
