package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CantidadDto extends BaseEntityDto {
    private UnidadDto unidad;
    private double valor;

    private CantidadDto(Cantidad cantidad) {
        super(cantidad);
    }

    public static CantidadDto from(Cantidad cantidad) {
        CantidadDto dto = new CantidadDto(cantidad);
        if (cantidad.tieneUnidad())
            dto.setUnidad(UnidadDto.from(cantidad.getUnidad()));
        dto.setValor(cantidad.getValor());
        return dto;
    }
}
