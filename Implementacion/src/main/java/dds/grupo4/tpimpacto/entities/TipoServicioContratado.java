package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "TipoServicioContratado")
@Table(name = "tipos_servicio_contratado")
public class TipoServicioContratado extends BaseEntity {

    private String nombre;

    // Hibernate
    protected TipoServicioContratado() {
    }

    public TipoServicioContratado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
