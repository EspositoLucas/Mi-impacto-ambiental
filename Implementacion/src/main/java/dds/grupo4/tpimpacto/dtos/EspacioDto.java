package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EspacioDto extends BaseEntityDto {
    private String nombre;
    private String tipoEspacio;
    private DireccionDto direccion;

    private EspacioDto(Espacio espacio) {
        super(espacio);
    }

    public static EspacioDto from(Espacio espacio) {
        EspacioDto dto = new EspacioDto(espacio);
        dto.setNombre(espacio.getNombre());
        dto.setTipoEspacio(espacio.getTipoEspacio().toString());
        if (espacio.getDireccion() != null) {
            dto.setDireccion(DireccionDto.from(espacio.getDireccion()));
        }
        return dto;
    }
}
