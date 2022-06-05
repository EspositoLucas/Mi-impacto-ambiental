package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoCombustible;

import javax.persistence.Entity;
import javax.persistence.Table;

// TODO: estoy casi seguro que esta clase no deberia existir
@Entity(name = "Combustible")
@Table(name = "combustibles")
<<<<<<< HEAD
@Inheritance(strategy = InheritanceType.JOINED)
public class Combustible extends BaseEntity{
     private Double combustibleInicial;
     private Double combustibleFinal;
     private TipoCombustible tipoCombustible ;
=======
public class Combustible extends BaseEntity {
    private Double combustibleInicial;
    private Double combustibleFinal;
    private TipoCombustible tipoCombustible;
>>>>>>> e1d416f4eef3b15244fab6944571afefdf3d9703

    public Combustible(Double combustibleInicial, Double combustibleFinal, TipoCombustible tipoCombustible) {
        this.combustibleInicial = combustibleInicial;
        this.combustibleFinal = combustibleFinal;
        this.tipoCombustible = tipoCombustible;
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
