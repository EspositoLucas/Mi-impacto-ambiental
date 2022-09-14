package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.trayecto.MiembroPorTrayecto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MiembroPorTrayectoDto extends BaseEntityDto {
    private IdTextPair miembro;
    private IdTextPair trayecto;
    private double peso;

    private MiembroPorTrayectoDto(MiembroPorTrayecto miembroPorTrayecto) {
        super(miembroPorTrayecto);
    }

    public static MiembroPorTrayectoDto from(MiembroPorTrayecto miembroPorTrayecto) {
        MiembroPorTrayectoDto dto = new MiembroPorTrayectoDto(miembroPorTrayecto);
        dto.setMiembro(new IdTextPair(miembroPorTrayecto.getMiembro()));
        dto.setTrayecto(new IdTextPair(miembroPorTrayecto.getTrayecto()));
        dto.setPeso(miembroPorTrayecto.getPeso());
        return dto;
    }
}
