package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearOrganizacionRequest {
    private String razonSocial;
    private String tipoOrganizacion;
    private String clasificacion;
    private CantidadDto factorK;
    private int cantDiasHabilesPorSemana;
}
