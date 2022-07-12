package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Miembro")
@Table(name = "miembros")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Miembro extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "persona", nullable = false)
    private Persona persona;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sector", nullable = false)
    private Sector sector;

    @ManyToMany(mappedBy = "miembros")
    private List<Tramo> tramos = new ArrayList<>();

    private CalculoHCTramos calculoHCTramos;

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
    } // Para saber que persona es un miembro a partir del documento

    // calculo HC de un miembro
    public double calculoHC() {
        // creo que el calculo HC de un miembro seria el HC de los tramos
        // que recorrio en un trayecto
        return this.calculoHCTramos.calculoHCTramos();
    }
}
