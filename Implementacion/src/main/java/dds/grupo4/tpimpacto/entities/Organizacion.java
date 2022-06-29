package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.Clasificacion;
import dds.grupo4.tpimpacto.enums.TipoOrganizacion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity(name = "Organizacion")
@Table(name = "organizaciones")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Organizacion extends BaseEntity {

    private String razonSocial;
    private TipoOrganizacion tipoOrganizacion;
    private Clasificacion clasificacion;
    private List<Sector> sectores = new ArrayList<>();

    private List<Contacto> contactos = new ArrayList<>();
    private List<Solicitud> solicitudes = new ArrayList<>();
    private List<Medicion> mediciones = new ArrayList<>();
    private CalculoHCTramo calculoHCTramo ;

    public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Clasificacion clasificacion, List<Sector> sectores, List<Contacto> contactos, List<Solicitud> solicitudes, List<Medicion> mediciones, CalculoHCTramo calculoHCTramo) {
        this.razonSocial = razonSocial;
        this.tipoOrganizacion = tipoOrganizacion;
        this.clasificacion = clasificacion;
        this.sectores = sectores;
        this.contactos = contactos;
        this.solicitudes = solicitudes;
        this.mediciones = mediciones;
        this.calculoHCTramo = calculoHCTramo;
    }

    public void addSector(Sector sector) {
        sectores.add(sector);
        sector.setOrganizacion(this);
    }

    public void addSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
        solicitud.setOrganizacion(this);
    }

    public Optional<Solicitud> getSolicitudDeMiembro(String documento) {
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getMiembro().getDocumento().equals(documento))
                .findFirst();
    }

    /**
     * Metodo para aceptar vinculacion del miembro con la organizacion
     */
    public void aceptarSolicitud(Solicitud solicitud) {
        solicitud.getSector().addMiembro(solicitud.getMiembro());
        this.solicitudes.remove(solicitud);
    }

    public List<Miembro> getMiembros() {  // Para saber los miembros que tiene una organizacion de cada sector que tiene
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public void addMedicion(Medicion medicion) {
        mediciones.add(medicion);
    }

    //    //Metodo para cargar las medidiciones del excel
//    public void cargarMediciones() {
//
//    }

    // calculo para HC

//    public double calculoHCTotal() {
//    return getMiembros().stream()
//            .flatMap(m -> m.getTramo().stream())
//            .distinct()
//            .collect(Collectors.toList());
//    }

}
