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
    private List<Tramo> tramos = new ArrayList<>();

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    public Organizacion getOrganizacion() {
        return this.sector.getOrganizacion();
    }

    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
    }

    public String getDocumento() {
        return persona.getDocumento();
    }

    // calculo para la huella de carbono

//    public double calculoHC() {
//        return ;
//    }

}
