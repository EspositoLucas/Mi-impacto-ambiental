package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "Lugar")
@Table(name = "lugares")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Lugar extends BaseEntity {
    private Direccion direccion;

    // Hibernate
    protected Lugar() {
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
