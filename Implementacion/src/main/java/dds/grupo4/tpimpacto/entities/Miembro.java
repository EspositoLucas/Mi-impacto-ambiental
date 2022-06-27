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

    public Miembro(Persona persona, Usuario usuario, Sector sector) {
        this.persona = persona;
        this.usuario = usuario;
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


    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
    }

    public Organizacion getOrganizacion() {
        return sector.getOrganizacion();
    } // Para saber a que organizacion pertenece un miembro a partir del sector

    public String getDocumento() {
        return persona.getDocumento();
    }  // Para saber que persona es un miembro a partir del documento


    // calculo para HC

//    public double calculoHC() {
//        return ;
//    }

}
