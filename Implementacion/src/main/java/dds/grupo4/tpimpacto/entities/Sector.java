package dds.grupo4.tpimpacto.entities;

import java.util.List;

public class Sector {
    private String nombre;
    private Organizacion organizacion;
    private List<Miembro> miembros;
    private EspacioTrabajo espacioTrabajo;

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

    public EspacioTrabajo getEspacioTrabajo() {
        return espacioTrabajo;
    }

    public void setEspacioTrabajo(EspacioTrabajo espacioTrabajo) {
        this.espacioTrabajo = espacioTrabajo;
    }
}
