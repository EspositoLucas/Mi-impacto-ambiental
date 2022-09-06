package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.units.Unidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Combustible")
@Table(name = "combustibles")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Combustible extends BaseEntity {

    private TipoCombustible tipoCombustible;

    @ManyToOne
    @JoinColumn(
            name = "unidad",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Combustibles_Unidad")
    )
    private Unidad unidad;

    public Combustible(TipoCombustible tipoCombustible, Unidad unidad) {
        this.tipoCombustible = tipoCombustible;
        this.unidad = unidad;
    }
}
