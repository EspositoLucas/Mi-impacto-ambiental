package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;

import java.util.List;
import java.util.stream.Collectors;

public class Organizacion {
    private String razonSocial;
    private TipoOrganizacion tipo;
    private List<Sector> sectores;
    private Clasificacion clasificacion;

    public Organizacion(String razonSocial, TipoOrganizacion tipo, List<Sector> sectores, Clasificacion clasificacion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.sectores = sectores;
        this.clasificacion = clasificacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public TipoOrganizacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrganizacion tipo) {
        this.tipo = tipo;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Miembro> getMiembros() {
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
