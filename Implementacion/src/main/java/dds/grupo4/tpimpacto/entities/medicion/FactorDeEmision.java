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
    private UnidadFactorEmision unidad;

    public FactorDeEmision(Double valor, UnidadFactorEmision unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }

    private void validarGramosEquivalentes() throws UnidadesIncorrectasException {
        if (!this.getUnidad().equals(UnidadFactorEmision.GCO2eq)) {
            throw new UnidadesIncorrectasException(
                    "La unidad a convertir debe ser GCO2eq");
        }
    }

    public FactorDeEmision pasarAUnidadDeHCConveniente() throws UnidadesIncorrectasException {
        this.validarGramosEquivalentes();
        if (valor >= 1000 && valor < 1000000) {
            this.valor = valor / 1000;
            this.unidad = UnidadFactorEmision.KGCO2eq;
        } else if (valor >= 1000000) {
            this.valor = valor / 1000000;
            this.unidad = UnidadFactorEmision.TNCO2eq;
        }
        return this;
    }

}
