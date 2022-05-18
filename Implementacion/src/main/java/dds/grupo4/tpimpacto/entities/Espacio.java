package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Espacio")
@Table(name = "espacios")
public class Espacio extends BaseEntity {

    private Direccion direccion;
    private String nombre;

    // Hibernate
    protected Espacio() {
    }

    public Espacio(Direccion direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public Espacio(Direccion direccion) {
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion ubicacionGeografica) {
        this.direccion = ubicacionGeografica;
    }

}
