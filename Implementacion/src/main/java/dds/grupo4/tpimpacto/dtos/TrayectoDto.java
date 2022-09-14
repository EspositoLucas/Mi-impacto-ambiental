package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TrayectoDto extends BaseEntityDto {
    private LugarDto lugarInicio;
    private LugarDto lugarFin;
    private List<MiembroPorTrayectoDto> miembrosPorTrayecto;
    private List<TramoDto> tramos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private TrayectoDto(Trayecto trayecto) {
        super(trayecto);
    }

    public static TrayectoDto from(Trayecto trayecto) {
        TrayectoDto dto = new TrayectoDto(trayecto);
        dto.setLugarInicio(LugarDto.from(trayecto.getInicio()));
        dto.setLugarFin(LugarDto.from(trayecto.getFin()));
        dto.setMiembrosPorTrayecto(trayecto.getMiembrosPorTrayecto().stream()
                .map(MiembroPorTrayectoDto::from).collect(Collectors.toList()));
        dto.setTramos(trayecto.getTramos().stream()
                .map(TramoDto::from).collect(Collectors.toList()));
        dto.setFechaInicio(trayecto.getFechaInicio());
        dto.setFechaFin(trayecto.getFechaFin());
        return dto;
    }
}
