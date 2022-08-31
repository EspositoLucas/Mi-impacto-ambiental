package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspacioDto {
    private Long id;
    private String nombre;
    private String tipoEspacio;
    private DireccionDto direccion;

    public static EspacioDto from(Espacio espacio) {
        EspacioDto dto = new EspacioDto();
        dto.setId(espacio.getId());
        dto.setNombre(espacio.getNombre());
        dto.setTipoEspacio(espacio.getTipoEspacio().toString());
        if (espacio.getDireccion() != null) {
            dto.setDireccion(DireccionDto.from(espacio.getDireccion()));
        }
        return dto;
    }
}
