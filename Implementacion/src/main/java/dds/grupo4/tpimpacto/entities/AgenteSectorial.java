package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AgenteSectorial")
@Table(name = "agentes_sectoriales")

public class AgenteSectorial extends BaseEntity {
    private SectorTerritorial sectorTerritorial ;

    // Hibernate
    protected AgenteSectorial() {
    }
}

