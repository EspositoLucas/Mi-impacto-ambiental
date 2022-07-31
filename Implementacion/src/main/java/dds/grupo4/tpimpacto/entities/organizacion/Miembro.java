package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Miembro")
@Table(name = "miembros")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Miembro extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "persona", nullable = false, foreignKey = @ForeignKey(name = "FK_Miembros_Persona"))
    private Persona persona;

    @OneToOne
    private Usuario usuario;

    private Date fechaIngreso ;

    @ManyToOne
    @JoinColumn(name = "sector", nullable = false, foreignKey = @ForeignKey(name = "FK_Miembros_Sector"))
    private Sector sector;

    @ManyToMany(mappedBy = "miembros")
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
    }

    public String getDocumento() {
        return persona.getDocumento();
    }

}
