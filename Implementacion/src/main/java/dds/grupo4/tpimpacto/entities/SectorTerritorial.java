package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoSectorTerritorial;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "SectorTerritorial")
@Table(name = "sectores_territoriales")
public class SectorTerritorial extends BaseEntity {

    private List<AgenteSectorial> agentesSectoriales = new ArrayList<>();
    private List<Organizacion> organizaciones = new ArrayList<>();
    private TipoSectorTerritorial tipoSectorTerritorial;

    // Hibernate
    protected SectorTerritorial() {
    }

    public SectorTerritorial(List<AgenteSectorial> agentesSectoriales, List<Organizacion> organizaciones, TipoSectorTerritorial tipo) {
        this.agentesSectoriales = agentesSectoriales;
        this.organizaciones = organizaciones;
        this.tipoSectorTerritorial = tipo;
    }

    public List<AgenteSectorial> getAgentesSectoriales() {
        return agentesSectoriales;
    }

    public void setAgentesSectoriales(List<AgenteSectorial> agentesSectoriales) {
        this.agentesSectoriales = agentesSectoriales;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
    }

    public TipoSectorTerritorial getTipoSectorTerritorial() {
        return tipoSectorTerritorial;
    }

    public void setTipoSectorTerritorial(TipoSectorTerritorial tipoSectorTerritorial) {
        this.tipoSectorTerritorial = tipoSectorTerritorial;
    }

    // metodo Resultado HC total para SectoTerritorial

//    public Double calculoHCTotal {
//        return this.organizaciones
//                .stream()
//                .mapToInt(o -> o.calculoHCTotal)
//                .sum();
//    }
}
