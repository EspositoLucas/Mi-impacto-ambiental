package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "MedioDeTransporte")
@Table(name = "medios_de_transporte")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MedioDeTransporte extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "tipo_medio_de_transporte",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_MediosDeTransporte_TipoMedioDeTransporte")
    )
    private TipoMedioDeTransporte tipoMedioDeTransporte;

    public FactorDeEmision getFactorDeEmision() {
        return tipoMedioDeTransporte.getFactorDeEmision();
    }

    public abstract double getCombustibleConsumidoPorKm();

}
