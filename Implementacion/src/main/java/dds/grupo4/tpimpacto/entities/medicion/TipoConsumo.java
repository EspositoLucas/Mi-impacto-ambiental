package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "TipoConsumo")
@Table(name = "tipos_de_consumo")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoConsumo extends BaseEntity {
    private String nombre;
    private Actividad actividad;
    //private UnidadFactorEmision unidadFactorEmision; // no hace falta esto al tener el factor de emision ?

    @OneToOne
    @JoinColumn(
            name = "factor_de_emision",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_MedioDeTransporte_FactorDeEmision")
    )
    private FactorDeEmision factorDeEmision ;
    private String alcance;

    private MedioDeTransporte medioDeTransporte ;

    private double distanciaMediaRecorrida;

    private double peso;


    public TipoConsumo(String nombre, Actividad actividad, UnidadFactorEmision unidadFactorEmision, String alcance) {
        this.nombre = nombre;
        this.actividad = actividad;
       // this.unidadFactorEmision = unidadFactorEmision;
        this.alcance = alcance;
    }
    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setTipoConsumo(this);
    }

}
