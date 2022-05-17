package dds.grupo4.tpimpacto.entities;

import java.util.List;

public class Sector {
    private String nombre;
    private Organizacion organizacion;
    private List<Miembro> miembros;
    private Espacio espacio;

    public Sector(String nombre, Organizacion organizacion, List<Miembro> miembros, Espacio espacio) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.miembros = miembros;
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
