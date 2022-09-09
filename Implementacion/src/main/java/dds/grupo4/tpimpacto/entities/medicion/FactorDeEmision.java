package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioTransporte;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "FactorDeEmision")
@Table(name = "factores_de_emision")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FactorDeEmision extends BaseEntity {

    private TipoMedioTransporte tipoMedioDeTransporte;

    @OneToOne(mappedBy = "factorDeEmision")
    private TipoConsumo tipoConsumo;

    @Embedded
    private Cantidad cantidad;

    public FactorDeEmision(Cantidad cantidad) {
        this.cantidad = cantidad;
    }

}
