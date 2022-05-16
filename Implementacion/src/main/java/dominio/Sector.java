package dominio;

import java.util.List;

public class Sector {
    private String nombre;
    private Organizacion organizacion ;
    private List<Miembro> miembros ;
    private Espacio espacioTrabajo;

    public Sector(String nombre, Organizacion organizacion, List<Miembro> miembros, Espacio espacioTrabajo) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.miembros = miembros;
        this.espacioTrabajo = espacioTrabajo;
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

    public Espacio getEspacioTrabajo() {
        return espacioTrabajo;
    }

    public void setEspacioTrabajo(Espacio espacioTrabajo) {
        this.espacioTrabajo = espacioTrabajo;
    }
}
