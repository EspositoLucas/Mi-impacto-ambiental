package dds.grupo4.tpimpacto.entities.sectorTerritorial;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "AgenteSectorial")
@Table(name = "agentes_sectoriales")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgenteSectorial extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sector_territorial", nullable = false,
            foreignKey = @ForeignKey(name = "FK_AgentesSectoriales_SectorTerritorial"))
    private SectorTerritorial sectorTerritorial;

    private String nombre;
    private String apellido;

    public AgenteSectorial(SectorTerritorial sectorTerritorial, String nombre, String apellido) {
        this.sectorTerritorial = sectorTerritorial;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
