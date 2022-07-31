package dds.grupo4.tpimpacto.entities.sectorTerritorial;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
            joinColumns = @JoinColumn(name = "sector_territorial"),
            inverseJoinColumns = @JoinColumn(name = "organizacion"),
            foreignKey = @ForeignKey(name = "FK_OrganizacionesPorSectorTerritorial_SectorTerritorial"),
            inverseForeignKey = @ForeignKey(name = "FK_OrganizacionesPorSectorTerritorial_Organizacion")
    )
    private List<Organizacion> organizaciones = new ArrayList<>();

    private TipoSectorTerritorial tipoSectorTerritorial;

    public SectorTerritorial(List<AgenteSectorial> agentesSectoriales, List<Organizacion> organizaciones, TipoSectorTerritorial tipo) {
        this.agentesSectoriales = agentesSectoriales;
        this.organizaciones = organizaciones;
        this.tipoSectorTerritorial = tipo;
    }

}
