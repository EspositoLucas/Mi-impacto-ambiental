package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizacionConHC {
    private IdTextPair organizacion;
    private CantidadDto hc;

    public static OrganizacionConHC from(Organizacion organizacion, Cantidad hc) {
        OrganizacionConHC organizacionConHC = new OrganizacionConHC();
        organizacionConHC.setOrganizacion(new IdTextPair(organizacion));
        organizacionConHC.setHc(CantidadDto.from(hc));
        return organizacionConHC;
    }
}
