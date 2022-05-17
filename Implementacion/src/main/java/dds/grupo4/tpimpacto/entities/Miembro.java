package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoDocumento;

import java.util.List;
import java.util.stream.Collectors;

public class Miembro {

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private List<Sector> sectores;
    private List<Trayecto> trayectos;

    public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento, List<Sector> sectores, List<Trayecto> trayectos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.sectores = sectores;
        this.trayectos = trayectos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    public List<Organizacion> getOrganizaciones() {
        return this.sectores.stream()
                .map(s -> s.getOrganizacion())
                .distinct()
                .collect(Collectors.toList());
    }


}
