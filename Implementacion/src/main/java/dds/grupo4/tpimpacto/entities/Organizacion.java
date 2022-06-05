package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Organizacion")
@Table(name = "organizaciones")
public class Organizacion extends BaseEntity {

    private String razonSocial;
    private TipoOrganizacion tipoOrganizacion;
    private Clasificacion clasificacion;
    private List<Sector> sectores = new ArrayList<>();
    private List<Contacto> contactos = new ArrayList<>();
    private List<Solicitud> solicitudes = new ArrayList<>();

    // Hibernate
    protected Organizacion() {
    }

    public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Clasificacion clasificacion) {
        this.razonSocial = razonSocial;
        this.tipoOrganizacion = tipoOrganizacion;
        this.clasificacion = clasificacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public TipoOrganizacion getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(TipoOrganizacion tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public List<Miembro> getMiembros() {
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public void addSector(Sector sector) {
        sectores.add(sector);
        sector.setOrganizacion(this);
    }

    /**
     * Metodo para aceptar vinculacion del miembro con la organizacion
     */
    public void aceptarSolicitud(Solicitud solicitud) throws Exception {
        if (!sectores.contains(solicitud.getSector())) {
            throw new Exception("Una organizacion no puede aceptar una solicitud de un sector que no es suyo");
        }

        solicitud.getSector().addMiembro(solicitud.getMiembro());
        this.solicitudes.remove(solicitud);
    }

//    //Metodo para cargar las medidiciones del excel
//    public void cargarMediciones() {
//
//    }

    // calculo para la huella de carbono

//    public double calculoHC() {
//
//        return 0;
//    }

    // calculo HC promedio
//    public double calculoHCPromedio() {
//
//        return 0;
//    }
}
