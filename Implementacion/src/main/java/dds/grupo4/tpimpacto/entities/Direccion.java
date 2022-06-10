package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Direccion")
@Table(name = "direcciones")
public class Direccion extends BaseEntity {

    private String calle;
    private String altura;

    private String pais;
    private String provincia;
    private String municipio;
    private String localidad;

    private String barrio;
    private int codigoPostal;

    // Hibernate
    protected Direccion() {
    }

    public Direccion(String calle, String altura, String pais, String provincia, String municipio, String localidad, String barrio, int codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.pais = pais;
        this.provincia = provincia;
        this.municipio = municipio;
        this.localidad = localidad;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
