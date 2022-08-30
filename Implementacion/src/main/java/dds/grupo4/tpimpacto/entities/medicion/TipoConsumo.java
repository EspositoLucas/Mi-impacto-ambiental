package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
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
    // private UnidadFactorEmision unidadFactorEmision; // no hace falta esto al tener el factor de emision ?

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "factor_de_emision",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_TipoConsumo_FactorDeEmision")
    )
    private FactorDeEmision factorDeEmision;
    private String alcance;


    public TipoConsumo(String nombre, Actividad actividad, FactorDeEmision factorDeEmision, String alcance) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.factorDeEmision = factorDeEmision;
        this.alcance = alcance;
    }

    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setTipoConsumo(this);
    }

}
