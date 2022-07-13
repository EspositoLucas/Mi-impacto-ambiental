package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.entities.enums.TipoSectorTerritorial;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SectorTerritorial")
@Table(name = "sectores_territoriales")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SectorTerritorial extends BaseEntity {

    @OneToMany(mappedBy = "sectorTerritorial")
    private List<AgenteSectorial> agentesSectoriales = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "organizaciones_por_sector_territorial",
        joinColumns = @JoinColumn(name = "???sector_territorial???"),
        inverseJoinColumns = @JoinColumn(name = "organizacion")
    )
    private List<Organizacion> organizaciones = new ArrayList<>();

    private TipoSectorTerritorial tipoSectorTerritorial;

    public SectorTerritorial(List<AgenteSectorial> agentesSectoriales, List<Organizacion> organizaciones, TipoSectorTerritorial tipo) {
        this.agentesSectoriales = agentesSectoriales;
        this.organizaciones = organizaciones;
        this.tipoSectorTerritorial = tipo;
    }

    // metodo Resultado HC total para SectoTerritorial

//    public Double calculoHCTotal {
//        return this.organizaciones
//                .stream()
//                .mapToInt(o -> o.calculoHCTotal)
//                .sum();
//    }
}
