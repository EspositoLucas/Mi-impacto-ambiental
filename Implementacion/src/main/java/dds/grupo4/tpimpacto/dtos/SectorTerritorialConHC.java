package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SectorTerritorialConHC {
    private IdTextPair sectorTerritorial;
    private CantidadDto hc;

    public static SectorTerritorialConHC from(SectorTerritorial sectorTerritorial, Cantidad hc) {
        SectorTerritorialConHC sectorTerritorialConHC = new SectorTerritorialConHC();
        sectorTerritorialConHC.setSectorTerritorial(new IdTextPair(sectorTerritorial));
        sectorTerritorialConHC.setHc(CantidadDto.from(hc));
        return sectorTerritorialConHC;
    }
}
