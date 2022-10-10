package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnidadDto extends BaseEntityDto {
    private String nombre;
    private String simbolo;
    private String tipoUnidad;

    private UnidadDto(Unidad unidad) {
        super(unidad);
    }

    public static UnidadDto from(Unidad unidad) {
        UnidadDto dto = new UnidadDto(unidad);
        dto.setNombre(unidad.getNombre());
        dto.setSimbolo(unidad.getSimbolo());
        if (unidad.getTipoUnidad() != null)
            dto.setTipoUnidad(unidad.getTipoUnidad().getNombre());
        return dto;
    }
}
