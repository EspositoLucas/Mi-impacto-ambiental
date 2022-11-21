package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.utils.DateTimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MiembroDto extends BaseEntityDto {
    private IdTextPair persona;
    private IdTextPair usuario;
    private IdTextPair organizacion;
    private IdTextPair sector;
    private String fechaIngreso;
    private Long idSolicitud;

    private MiembroDto(Miembro miembro) {
        super(miembro);
    }

    public static MiembroDto from(Miembro miembro) {
        MiembroDto dto = new MiembroDto(miembro);
        dto.setPersona(new IdTextPair(miembro.getPersona()));
        if (miembro.getUsuario() != null) {
            dto.setUsuario(new IdTextPair(miembro.getUsuario()));
        }
        if (miembro.getSector() != null) {
            dto.setOrganizacion(new IdTextPair(miembro.getOrganizacion()));
            dto.setSector(new IdTextPair(miembro.getSector()));
        }
        if (miembro.getSolicitud() != null && miembro.getSolicitud().isActivo()) {
            dto.setIdSolicitud(miembro.getSolicitud().getId());
        } else {
            dto.setFechaIngreso(DateTimeUtils.dateToString(miembro.getFechaIngreso(), true));
        }
        return dto;
    }
}
