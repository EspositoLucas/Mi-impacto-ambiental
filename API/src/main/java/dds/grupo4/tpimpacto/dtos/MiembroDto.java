package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MiembroDto extends BaseEntityDto {
    private String nombre;
    private String apellido;
    private String username;
    private IdTextPair sector;
    private Long idSolicitud;

    private MiembroDto(Miembro miembro) {
        super(miembro);
    }

    public static MiembroDto from(Miembro miembro) {
        MiembroDto dto = new MiembroDto(miembro);
        dto.setNombre(miembro.getPersona().getNombre());
        dto.setApellido(miembro.getPersona().getApellido());
        if (miembro.getUsuario() != null) {
            dto.setUsername(miembro.getUsuario().getUsername());
        }
        if (miembro.getSector() != null) {
            dto.setSector(new IdTextPair(miembro.getSector()));
        }
        if (miembro.getSolicitud() != null && miembro.getSolicitud().isActivo()) {
            dto.setIdSolicitud(miembro.getSolicitud().getId());
        }
        return dto;
    }
}
