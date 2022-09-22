package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioDeTransporte;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "FactorDeEmision")
@Table(name = "factores_de_emision")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FactorDeEmision extends BaseEntity {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "tipo_medio_de_transporte",
            foreignKey = @ForeignKey(name = "FK_FactoresDeEmision_TipoMedioDeTransporte")
    )
    private TipoMedioDeTransporte tipoMedioDeTransporte;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "tipo_consumo",
            foreignKey = @ForeignKey(name = "FK_FactoresDeEmision_TipoConsumo")
    )
    private TipoConsumo tipoConsumo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cantidad", nullable = false, foreignKey = @ForeignKey(name = "FK_FactoresDeEmision_Cantidad"))
    private Cantidad cantidad;

    public FactorDeEmision(Cantidad cantidad) {
        this.cantidad = cantidad;
    }

}
