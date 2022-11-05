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
    private IdTextPair unidad;
    private double valor;
    private String text;

    private CantidadDto(Cantidad cantidad) {
        super(cantidad);
    }

    public static CantidadDto from(Cantidad cantidad) {
        CantidadDto dto = new CantidadDto(cantidad);
        if (cantidad.tieneUnidad()) {
            dto.setUnidad(new IdTextPair(cantidad.getUnidad()));
        }
        dto.setValor(cantidad.getValor());
        dto.setText();
        return dto;
    }

    private void setText() {
        this.text = this.unidad != null
                ? this.valor + " " + this.unidad.getText()
                : String.valueOf(this.valor);
    }
}
