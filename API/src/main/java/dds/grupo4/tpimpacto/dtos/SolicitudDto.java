package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolicitudDto extends BaseEntityDto {
    private IdTextPair miembro;
    private IdTextPair organizacion;
    private IdTextPair sector;
    private boolean activo;

    private SolicitudDto(Solicitud solicitud) {
        super(solicitud);
    }

    public static SolicitudDto from(Solicitud solicitud) {
        SolicitudDto dto = new SolicitudDto(solicitud);
        dto.setMiembro(new IdTextPair(solicitud.getMiembro()));
        dto.setOrganizacion(new IdTextPair(solicitud.getSector().getOrganizacion()));
        dto.setSector(new IdTextPair(solicitud.getSector()));
        dto.setActivo(solicitud.isActivo());
        return dto;
    }
}
