package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.RegistroCalculoHCTrayecto;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "miembro", cascade = CascadeType.ALL)
    private List<RegistroCalculoHCTrayecto> registrosCalculoHCTrayectos;

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

    public List<Trayecto> getTrayectosRealizadosEnFecha(LocalDate date) {
        return tramos.stream()
                .map(Tramo::getTrayecto)
                .filter(trayecto -> trayecto.seRealizaEnFecha(date))
                .distinct()
                .collect(Collectors.toList());
    }

    public Cantidad getHCDeTrayecto(Trayecto trayecto) {
        return registrosCalculoHCTrayectos.stream()
                .filter(registro -> registro.getTrayecto().equals(trayecto))
                .findFirst().map(RegistroCalculoHCTrayecto::getValor)
                .orElseThrow(() -> new IllegalArgumentException("El Miembro no tiene ningun registro de HC para el Trayecto especificado"));
    }

    @Override
    public String toString() {
        return persona.toString();
    }
}
