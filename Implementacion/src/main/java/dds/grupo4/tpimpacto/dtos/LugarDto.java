package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LugarDto extends BaseEntityDto {
    private DireccionDto direccion;
    private String tipo; // "Espacio" o "Parada"

    private LugarDto(Lugar lugar) {
        super(lugar);
    }

    public static LugarDto from(Lugar lugar) {
        LugarDto dto = new LugarDto(lugar);
        dto.setDireccion(DireccionDto.from(lugar.getDireccion()));
        dto.setTipo(getTipo(lugar));
        return dto;
    }

    private static String getTipo(Lugar lugar) {
        if (lugar instanceof Espacio)
            return "Espacio";
        if (lugar instanceof Parada)
            return "Parada";
        return "?";
    }
}
