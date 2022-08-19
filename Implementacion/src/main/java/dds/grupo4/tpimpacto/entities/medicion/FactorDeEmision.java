package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
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

    // TODO: ver si no depende tambien de la Organizacion (ademas del MedioDeTransporte)
    @OneToOne(mappedBy = "factorDeEmision")
    private MedioDeTransporte medioDeTransporte;
    @OneToOne(mappedBy = "factorDeEmision")
    private TipoConsumo tipoConsumo ;
    private Double valor;
    private UnidadFactorEmision unidad;

    public FactorDeEmision(Double valor, UnidadFactorEmision unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }

}
