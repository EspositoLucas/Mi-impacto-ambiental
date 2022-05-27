package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoSectorTerritorial;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


@Entity(name = "SectorTerritorial")
@Table(name = "sectores_territoriales")
public class SectorTerritorial extends BaseEntity {

    private List<AgenteSectorial> agentesSectoriales;
    private List<Organizacion> organizaciones;
    private TipoSectorTerritorial tipo;

    // Hibernate
    protected SectorTerritorial() {
    }
}
