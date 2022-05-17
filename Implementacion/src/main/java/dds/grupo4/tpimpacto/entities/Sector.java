package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Sector")
@Table(name = "sectores")
public class Sector extends BaseEntity {

    private String nombre;
    private Organizacion organizacion;
    private Espacio espacio;
    private List<Miembro> miembros = new ArrayList<>();

    // Hibernate
    protected Sector() {
    }

    public Sector(String nombre, Organizacion organizacion, Espacio espacio) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.espacio = espacio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }
}
