package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClasificacionConHC {
    private IdTextPair clasificacion;
    private CantidadDto hc;

    public static ClasificacionConHC from(Clasificacion clasificacion, Cantidad hc) {
        ClasificacionConHC clasificacionConHC = new ClasificacionConHC();
        clasificacionConHC.setClasificacion(new IdTextPair(clasificacion.ordinal(), clasificacion.toString()));
        clasificacionConHC.setHc(CantidadDto.from(hc));
        return clasificacionConHC;
    }
}
