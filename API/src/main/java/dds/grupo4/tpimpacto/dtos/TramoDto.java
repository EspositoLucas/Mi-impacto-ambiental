package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.utils.ListUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TramoDto extends BaseEntityDto {
    private IdTextPair medioDeTransporte;
    private LugarDto lugarInicio;
    private LugarDto lugarFin;
    private List<IdTextPair> miembros;
    private CantidadDto distanciaRecorrida;

    private TramoDto(Tramo tramo) {
        super(tramo);
    }

    public static TramoDto from(Tramo tramo) {
        TramoDto dto = new TramoDto(tramo);
        dto.setMedioDeTransporte(new IdTextPair(tramo.getMedioDeTransporte()));
        dto.setLugarInicio(LugarDto.from(tramo.getLugarInicio()));
        dto.setLugarFin(LugarDto.from(tramo.getLugarFin()));
        dto.setMiembros(ListUtils.toIdTextPairList(tramo.getMiembros()));
        dto.setDistanciaRecorrida(CantidadDto.from(tramo.getDistanciaRecorrida()));
        return dto;
    }
}
