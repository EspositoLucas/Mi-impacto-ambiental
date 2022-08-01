package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Solicitud")
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Solicitud extends BaseEntity {

    // Tal vez esto tendria que ser un OneToOne
    @ManyToOne
    @JoinColumn(name = "miembro", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Miembro"))
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "sector", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Sector"))
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Organizacion"))
    private Organizacion organizacion;

    public Solicitud(Miembro miembro, Sector sector, Organizacion organizacion) {
        this.miembro = miembro;
        this.sector = sector;
        this.organizacion = organizacion;
    }

}
