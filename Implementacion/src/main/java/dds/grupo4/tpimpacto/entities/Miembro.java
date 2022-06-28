package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Miembro")
@Table(name = "miembros")
public class Miembro extends BaseEntity {
    @Getter
    @Setter
    private Persona persona;

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private Sector sector;

    @Getter
    @Setter
    private List<Tramo> tramos = new ArrayList<>();

    // Hibernate
    protected Miembro() {
    }

    public Miembro(Persona persona, Usuario usuario, Sector sector) {
        this.persona = persona;
        this.usuario = usuario;
        this.sector = sector;
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
