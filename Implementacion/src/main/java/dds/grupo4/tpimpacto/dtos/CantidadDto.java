package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.units.Cantidad;
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
    private double valor;

    public static CantidadDto from(Cantidad cantidad) {
        CantidadDto dto = new CantidadDto();
        dto.setUnidad(cantidad.getUnidad().toString());
        dto.setValor(cantidad.getValor());
        return dto;
    }
}
