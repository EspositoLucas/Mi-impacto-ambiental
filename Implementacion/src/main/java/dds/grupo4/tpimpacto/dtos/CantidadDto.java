package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CantidadDto {
    private String unidad;
    private Long idUnidad;
    private double valor;

    public static CantidadDto from(Cantidad cantidad) {
        CantidadDto dto = new CantidadDto();
        dto.setUnidad(Unidad.toString(cantidad.getUnidad()));
        if (cantidad.tieneUnidad()) {
            dto.setIdUnidad(cantidad.getUnidad().getId());
        }
        dto.setValor(cantidad.getValor());
        return dto;
    }
}
