package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.medioTransporte.*;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedioDeTransporteDto extends BaseEntityDto {
    private String tipo;
    private String descripcion;

    private MedioDeTransporteDto(MedioDeTransporte medioDeTransporte) {
        super(medioDeTransporte);
    }

    public static MedioDeTransporteDto from(MedioDeTransporte medioDeTransporte) {
        MedioDeTransporteDto dto = new MedioDeTransporteDto(medioDeTransporte);
        dto.setTipo(getTipo(medioDeTransporte));
        dto.setDescripcion(medioDeTransporte.toString());
        return dto;
    }

    private static String getTipo(MedioDeTransporte medioDeTransporte) {
        if (medioDeTransporte instanceof TransportePublico)
            return "Transporte publico";
        if (medioDeTransporte instanceof VehiculoNoContaminante)
            return "Vehiculo no contaminante";
        if (medioDeTransporte instanceof VehiculoParticular)
            return "Vehiculo particular";
        return "?";
    }
}
