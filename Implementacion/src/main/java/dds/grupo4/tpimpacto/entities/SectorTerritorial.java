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

<<<<<<< HEAD
    public SectorTerritorial(List<AgenteSectorial> agentesSectoriales, List<Organizacion> organizaciones, TipoSectorTerritorial tipo) {
        this.agentesSectoriales = agentesSectoriales;
        this.organizaciones = organizaciones;
        this.tipo = tipo;
    }

=======
>>>>>>> e1d416f4eef3b15244fab6944571afefdf3d9703
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

<<<<<<< HEAD
    public TipoSectorTerritorial getTipo() {
        return tipo;
    }

    public void setTipo(TipoSectorTerritorial tipo) {
        this.tipo = tipo;
=======
    public TipoSectorTerritorial getTipoSectorTerritorial() {
        return tipoSectorTerritorial;
    }

    public void setTipoSectorTerritorial(TipoSectorTerritorial tipoSectorTerritorial) {
        this.tipoSectorTerritorial = tipoSectorTerritorial;
>>>>>>> e1d416f4eef3b15244fab6944571afefdf3d9703
    }

    // metdod Resultado HC para SectoTerritorial

//    public Double calculoHC {
//        return this.organizaciones
//                .stream()
//                .mapToInt(o -> o.calculoHC)
//                .sum();
//    }
}
