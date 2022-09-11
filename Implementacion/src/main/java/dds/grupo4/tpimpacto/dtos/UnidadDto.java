package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.units.Unidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDto {
    private long id;
    private String nombre;
    private String simbolo;
    private String tipoUnidad;

    public static UnidadDto from(Unidad unidad) {
        UnidadDto dto = new UnidadDto();
        dto.setId(unidad.getId());
        dto.setNombre(unidad.getNombre());
        dto.setSimbolo(unidad.getSimbolo());
        if (unidad.getTipoUnidad() != null)
            dto.setTipoUnidad(unidad.getTipoUnidad().getNombre());
        return dto;
    }
}
