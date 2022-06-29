package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Miembro")
@Table(name = "miembros")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Miembro extends BaseEntity {

    private Persona persona;
    private Usuario usuario;
    private Sector sector;
    private List<Tramo> tramos = new ArrayList<>();

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


}
