package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MiembroDto {
    private long id;
    private String nombre;
    private String apellido;
    private String username;
    private IdTextPair sector;
    private Long idSolicitud;

    public static MiembroDto from(Miembro miembro) {
        MiembroDto dto = new MiembroDto();
        dto.setId(miembro.getId());
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
