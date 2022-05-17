package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Espacio")
@Table(name = "espacios")
public class Espacio extends BaseEntity {

    private Direccion ubicacionGeografica;

    // Hibernate
    protected Espacio() {
    }

    public Espacio(Direccion ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

    public Direccion getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public void setUbicacionGeografica(Direccion ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

}
