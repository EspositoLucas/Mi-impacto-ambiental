package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "organizacion")
    private List<Sector> sectores = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Contacto> contactos = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion")
    private List<Solicitud> solicitudes = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion")
    private List<Medicion> mediciones = new ArrayList<>();

    @ManyToMany(mappedBy = "organizaciones")
    private List<SectorTerritorial> sectoresTerritoriales = new ArrayList<>();

    private Double factorK;

    public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Clasificacion clasificacion) {
        this.razonSocial = razonSocial;
        this.tipoOrganizacion = tipoOrganizacion;
        this.clasificacion = clasificacion;
    }

    public void addSector(Sector sector) {
        sectores.add(sector);
        sector.setOrganizacion(this);
    }

    public void addContacto(Contacto contacto) {
        contactos.add(contacto);
        contacto.setOrganizacion(this);
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

    public List<Miembro> getMiembros() { // Para saber los miembros que tiene una organizacion de cada sector que tiene
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public void addMedicion(Medicion medicion) {
        mediciones.add(medicion);
    }

    public List<Tramo> getTramosDeMiembros() {
        return getMiembros().stream()
                .flatMap(m -> m.getTramos().stream())
                .distinct()
                .collect(Collectors.toList());
    }

}
