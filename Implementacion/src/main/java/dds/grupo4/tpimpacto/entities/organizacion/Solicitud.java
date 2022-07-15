package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Solicitud")
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Solicitud extends BaseEntity {

    // Tal vez esto tendria que ser un OneToOne
    @ManyToOne
    @JoinColumn(name = "miembro", nullable = false)
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "sector", nullable = false)
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false)
    private Organizacion organizacion; // para persistencia

    public Solicitud(Miembro miembro, Sector sector) {
        this.miembro = miembro;
        this.sector = sector;
    }

}
