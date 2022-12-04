package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.medicion.RegistroCalculoHCDatoActividad;
import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Organizacion")
@Table(name = "organizaciones")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Organizacion extends BaseEntity {

    private String razonSocial;
    private TipoOrganizacion tipoOrganizacion;
    private Clasificacion clasificacion;
    private int cantDiasHabilesPorSemana;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factor_k", nullable = false, foreignKey = @ForeignKey(name = "FK_Organizaciones_FactorK"))
    private Cantidad factorK;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Sector> sectores = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacto> contactos = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Medicion> mediciones = new ArrayList<>();

    @ManyToMany(mappedBy = "organizaciones")
    private List<SectorTerritorial> sectoresTerritoriales = new ArrayList<>();

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<RegistroCalculoHCDatoActividad> registrosCalculoHCDatoActividad = new ArrayList<>();

    public Organizacion(String razonSocial, TipoOrganizacion tipoOrganizacion, Clasificacion clasificacion,
                        Cantidad factorK, int cantDiasHabilesPorSemana) {
        this.razonSocial = razonSocial;
        this.tipoOrganizacion = tipoOrganizacion;
        this.clasificacion = clasificacion;
        this.factorK = factorK;
        this.cantDiasHabilesPorSemana = cantDiasHabilesPorSemana;
    }

    public void addSector(Sector sector) {
        sectores.add(sector);
        sector.setOrganizacion(this);
    }

    public void addContacto(Contacto contacto) {
        contactos.add(contacto);
        contacto.setOrganizacion(this);
    }

    public List<Miembro> getMiembros() { // Para saber los miembros que tiene una organizacion de cada sector que tiene
        return sectores.stream()
                .flatMap(s -> s.getMiembros().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Trayecto> getTrayectosRealizadosPorMiembrosEnFecha(LocalDate date) {
        return getMiembros().stream()
                .flatMap(miembro -> miembro.getTrayectosRealizadosEnFecha(date).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Solicitud> getSolicitudes() {
        return sectores.stream()
                .flatMap(sector -> sector.getSolicitudes().stream())
                .collect(Collectors.toList());
    }

    public void addMediciones(List<Medicion> mediciones) {
        mediciones.forEach(this::addMedicion);
    }

    public void addMedicion(Medicion medicion) {
        mediciones.add(medicion);
        medicion.setOrganizacion(this);
    }

    public void addRegistroCalculoHC(RegistroCalculoHCDatoActividad registroCalculoHC) {
        registroCalculoHC.setOrganizacion(this);
        registrosCalculoHCDatoActividad.add(registroCalculoHC);
    }

    public List<Tramo> getTramosDeMiembros() {
        return getMiembros().stream()
                .flatMap(m -> m.getTramos().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return razonSocial;
    }
}
