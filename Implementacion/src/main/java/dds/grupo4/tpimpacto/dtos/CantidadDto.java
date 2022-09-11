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
    private Long id;
    private UnidadDto unidad;
    private double valor;

    public static CantidadDto from(Cantidad cantidad) {
        CantidadDto dto = new CantidadDto();
        dto.setId(cantidad.getId());
        if (cantidad.tieneUnidad())
            dto.setUnidad(UnidadDto.from(cantidad.getUnidad()));
        dto.setValor(cantidad.getValor());
        return dto;
    }
}
