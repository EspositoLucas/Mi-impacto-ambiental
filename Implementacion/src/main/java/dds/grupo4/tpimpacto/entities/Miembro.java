package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Miembro")
@Table(name = "miembros")
public class Miembro extends BaseEntity {

    private Persona persona;
    private Usuario usuario;
    private Sector sector;
    private List<Trayecto> trayectos = new ArrayList<>();

    // Hibernate
    protected Miembro() {
    }

    public Miembro(Persona persona, Sector sector) {
        this.persona = persona;
        this.sector = sector;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    // Metodo para agregar trayecto
    public void agregarTrayecto(Trayecto trayecto) {
        this.trayectos.add(trayecto);
    }

    public Organizacion getOrganizacion() {
        return this.sector.getOrganizacion();
    }

    // calculo para la huella de carbono

//    public double calculoHC() {
//        return ;
//    }

}
