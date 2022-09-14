package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "MiembroPorTrayecto")
@Table(name = "miembros_por_trayecto")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MiembroPorTrayecto extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "miembro", nullable = false, foreignKey = @ForeignKey(name = "FK_MiembrosPorTrayecto_Miembro"))
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "trayecto", nullable = false, foreignKey = @ForeignKey(name = "FK_MiembrosPorTrayecto_Trayecto"))
    private Trayecto trayecto;

    private double peso;

    public MiembroPorTrayecto(Miembro miembro, Trayecto trayecto, double peso) {
        this.miembro = miembro;
        this.trayecto = trayecto;
        this.peso = peso;
    }
}
