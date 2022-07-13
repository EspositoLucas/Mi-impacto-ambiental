package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Direccion")
@Table(name = "direcciones")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Direccion extends BaseEntity {

    private String calle;
    private String altura;
    private String pais;
    private String provincia;
    private String municipio;
    private String localidad;
    private String barrio;
    private int codigoPostal;

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

}
