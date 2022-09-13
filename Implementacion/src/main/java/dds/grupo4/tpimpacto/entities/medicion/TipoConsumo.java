package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "TipoConsumo")
@Table(name = "tipos_de_consumo")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoConsumo extends BaseEntity {
    private String nombre;
    private Actividad actividad;
    private String alcance;

    @ManyToOne
    @JoinColumn(name = "unidad", foreignKey = @ForeignKey(name = "FK_TiposDeConsumo_Unidad"))
    private Unidad unidad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "factor_de_emision",
            foreignKey = @ForeignKey(name = "FK_TipoConsumo_FactorDeEmision")
    )
    private FactorDeEmision factorDeEmision;

    public TipoConsumo(String nombre, Actividad actividad, String alcance, Unidad unidad) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.alcance = alcance;
        this.unidad = unidad;
    }

    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setTipoConsumo(this);
    }

    public boolean tieneValorNumerico() {
        // Si el TipoConsumo no tiene unidad, es porque su valor no se mide con numeros
        return unidad != null;
    }

}
