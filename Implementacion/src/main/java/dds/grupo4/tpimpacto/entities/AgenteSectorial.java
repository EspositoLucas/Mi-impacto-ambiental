package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AgenteSectorial")
@Table(name = "agentes_sectoriales")
public class AgenteSectorial extends BaseEntity {
    private SectorTerritorial sectorTerritorial;
    private String nombre;
    private String apellido;

    // Hibernate
    protected AgenteSectorial() {
    }

    // Dar de alta

    public AgenteSectorial(SectorTerritorial sectorTerritorial, String nombre, String apellido) {
        this.sectorTerritorial = sectorTerritorial;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public SectorTerritorial getSectorTerritorial() {
        return sectorTerritorial;
    }

    public void setSectorTerritorial(SectorTerritorial sectorTerritorial) {
        this.sectorTerritorial = sectorTerritorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
