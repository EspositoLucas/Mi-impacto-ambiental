package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoCombustible;
import dds.grupo4.tpimpacto.enums.UnidadCombustible;

import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: estoy casi seguro que esta clase no deberia existir
@Entity(name = "Combustible")
@Table(name = "combustibles")
public class Combustible extends BaseEntity {
    private Double combustibleInicial;
    private Double combustibleFinal;
    private TipoCombustible tipoCombustible;
    private UnidadCombustible unidad ;


    public Combustible(Double combustibleInicial, Double combustibleFinal, TipoCombustible tipoCombustible, UnidadCombustible unidad) {
        this.combustibleInicial = combustibleInicial;
        this.combustibleFinal = combustibleFinal;
        this.tipoCombustible = tipoCombustible;
        this.unidad = unidad;
    }

    protected Combustible() {
    }

    public Double getCombustibleInicial() {
        return combustibleInicial;
    }

    public void setCombustibleInicial(Double combustibleInicial) {
        this.combustibleInicial = combustibleInicial;
    }

    public Double getCombustibleFinal() {
        return combustibleFinal;
    }

    public void setCombustibleFinal(Double combustibleFinal) {
        this.combustibleFinal = combustibleFinal;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

}
