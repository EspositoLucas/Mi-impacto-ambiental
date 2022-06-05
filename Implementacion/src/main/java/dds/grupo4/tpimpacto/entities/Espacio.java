package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoEspacio;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Espacio")
@Table(name = "espacios")

public class Espacio extends Lugar {

    //private Direccion direccion;
    private String nombre;
    private TipoEspacio tipoEspacio;
    // Hibernate
    protected Espacio() {
    }

    public Espacio(String nombre, TipoEspacio tipoEspacio) {
        this.nombre = nombre;
        this.tipoEspacio = tipoEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }
}
