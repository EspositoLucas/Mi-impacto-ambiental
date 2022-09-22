package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity(name = "RegistroCalculoHCTrayecto")
@Table(name = "registros_calculo_hc_trayecto")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistroCalculoHCTrayecto extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trayecto", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCTrayecto_Trayecto"))
    private Trayecto trayecto;

    @ManyToOne
    @JoinColumn(name = "miembro", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCTrayecto_Miembro"))
    private Miembro miembro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCTrayecto_Valor"))
    private Cantidad valor;

    public RegistroCalculoHCTrayecto(Miembro miembro, Cantidad valor) {
        this.miembro = miembro;
        this.valor = valor;
    }
}
