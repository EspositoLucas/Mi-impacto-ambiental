package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;

import javax.persistence.*;

@Entity(name = "MedioDeTransporte")
@Table(name = "medios_de_transporte")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MedioDeTransporte extends BaseEntity {

    protected final String API_TOKEN = "dbNsJcYAneNbF8+i9DX735F7KR7mPZVELmu1wN+Nx0o=";

    private String nombre ;


    // ? Esto supone que todas las Organizaciones usan el mismo FactorDeEmision para cada MedioDeTransporte.
    // Si cada Organizacion puede tener su propio FE para cada MedioDeTransporte, entonces habria que hacer
    // un ManyToMany con una tabla intermedia.
    @OneToOne
    @JoinColumn(
            name = "factor_de_emision",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_MedioDeTransporte_FactorDeEmision")
    )
    private FactorDeEmision factorDeEmision;

    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setMedioDeTransporte(this);
    }

    public abstract double getCombustibleConsumidoPorKm();

}
