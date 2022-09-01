package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoMedioTransporte;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double valor;

    public FactorDeEmision(Double valor) {
        this.valor = valor;
    }



}
