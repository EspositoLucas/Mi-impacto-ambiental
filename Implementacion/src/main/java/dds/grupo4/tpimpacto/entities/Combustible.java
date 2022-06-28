package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoCombustible;
import dds.grupo4.tpimpacto.enums.UnidadCombustible;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: estoy casi seguro que esta clase no deberia existir
@Entity(name = "Combustible")
@Table(name = "combustibles")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Combustible extends BaseEntity {

    private Double combustibleInicial;
    private Double combustibleFinal;
    private TipoCombustible tipoCombustible;
    private UnidadCombustible unidad;

    public Combustible(Double combustibleInicial, Double combustibleFinal, TipoCombustible tipoCombustible, UnidadCombustible unidad) {
        this.combustibleInicial = combustibleInicial;
        this.combustibleFinal = combustibleFinal;
        this.tipoCombustible = tipoCombustible;
        this.unidad = unidad;
    }

}
