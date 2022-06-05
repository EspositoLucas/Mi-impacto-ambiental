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

    public SectorTerritorial(List<AgenteSectorial> agentesSectoriales, List<Organizacion> organizaciones, TipoSectorTerritorial tipo) {
        this.agentesSectoriales = agentesSectoriales;
        this.organizaciones = organizaciones;
        this.tipo = tipo;
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

    public TipoSectorTerritorial getTipo() {
        return tipo;
    }

    public void setTipo(TipoSectorTerritorial tipo) {
        this.tipo = tipo;
    }

    // metdod Resultado HC para SectoTerritorial

//    public Double calculoHC {
//        return this.organizaciones
//                .stream()
//                .mapToInt(o -> o.calculoHC)
//                .sum();
//    }
}
