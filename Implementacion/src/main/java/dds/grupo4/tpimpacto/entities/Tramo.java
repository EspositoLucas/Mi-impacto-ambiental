package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tramo")
@Table(name = "tramos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tramo extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trayecto", nullable = false)
    private Trayecto trayecto;

    @ManyToOne
    @JoinColumn(name = "???medio_de_transporte???", nullable = false)
    private MedioDeTransporte medioDeTransporte;

    @ManyToOne
    @JoinColumn(name = "???lugar_inicio???", nullable = false)
    private Lugar lugarInicio;

    @ManyToOne
    @JoinColumn(name = "???lugar_fin???", nullable = false)
    private Lugar lugarFin;

    @ManyToMany
    @JoinTable(name = "miembros_por_tramo", joinColumns = @JoinColumn(name = "tramo"), inverseJoinColumns = @JoinColumn(name = "miembro"))
    private List<Miembro> miembros = new ArrayList<>();

    private EstrategiaDistancia strategyDistanciaRecorrida ;
    public Tramo(Trayecto trayecto, MedioDeTransporte medioDeTransporte, Lugar lugarInicio, Lugar lugarFin, List<Miembro> miembros) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
        this.miembros = miembros;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.addTramo(this);
    }

    public double distancia() {
        return medioDeTransporte.distanciaRecorrida(lugarInicio, lugarFin);
    }

    // calculo para HC

    public double calculoHC() {
    return  medioDeTransporte.distanciaRecorrida(lugarInicio,lugarFin) * medioDeTransporte.getFactorEmision().getValor();
    }

}
