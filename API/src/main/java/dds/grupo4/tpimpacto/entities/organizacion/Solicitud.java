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

    @OneToOne
    @JoinColumn(name = "miembro", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Miembro"))
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "sector", nullable = false, foreignKey = @ForeignKey(name = "FK_Solicitudes_Sector"))
    private Sector sector;

    public Solicitud(Miembro miembro, Sector sector) {
        this.miembro = miembro;
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "{" +
                "miembro=" + miembro.toString() +
                ", sector=" + sector.toString() +
                '}';
    }
}
