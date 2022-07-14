package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "AgenteSectorial")
@Table(name = "agentes_sectoriales")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgenteSectorial extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "???sector_territorial???", nullable = false)
    private SectorTerritorial sectorTerritorial;

    private String nombre;
    private String apellido;

    public AgenteSectorial(SectorTerritorial sectorTerritorial, String nombre, String apellido) {
        this.sectorTerritorial = sectorTerritorial;
        this.nombre = nombre;
        this.apellido = apellido;
    }

}
