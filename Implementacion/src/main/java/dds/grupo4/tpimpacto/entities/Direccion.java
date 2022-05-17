package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Direccion")
@Table(name = "direcciones")
public class Direccion extends BaseEntity {

    private String calle;
    private int altura;
    private String barrio;
    private int codigoPostal;

    // Hibernate
    protected Direccion() {
    }

    public Direccion(String calle, int altura, String barrio, int codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
