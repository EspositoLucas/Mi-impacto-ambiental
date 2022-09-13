package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Miembro")
@Table(name = "miembros")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Miembro extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "persona", nullable = false, foreignKey = @ForeignKey(name = "FK_Miembros_Persona"))
    private Persona persona;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "FK_Miembros_Usuario"))
    private Usuario usuario;

    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "sector", foreignKey = @ForeignKey(name = "FK_Miembros_Sector"))
    private Sector sector;

    @OneToOne(mappedBy = "miembro")
    private Solicitud solicitud;

    @ManyToMany(mappedBy = "miembros")
    private List<Tramo> tramos = new ArrayList<>();

    public Miembro(Persona persona) {
        this.persona = persona;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setMiembro(this);
    }

    @Override
    public String toString() {
        return persona.toString();
    }
}
