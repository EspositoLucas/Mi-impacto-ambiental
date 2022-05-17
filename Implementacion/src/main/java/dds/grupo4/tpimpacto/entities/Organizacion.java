package dds.grupo4.tpimpacto.entities;

import java.util.List;

public class Organizacion {
    private String razonSocial;
    private TipoOrganizacion tipo;
    private List<Sector> sectores;
    private Clasificacion clasificacion;

    public Organizacion() {
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

//    public List<Miembro> getMiembros() {
//        return sectores.getMiembros();
//    }
}
