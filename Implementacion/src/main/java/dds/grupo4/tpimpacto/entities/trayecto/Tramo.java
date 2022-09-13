package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.services.calculodistancias.CalculadoraDistancias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tramo")
@Table(name = "tramos")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tramo extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "trayecto", nullable = false, foreignKey = @ForeignKey(name = "FK_Tramos_Trayecto"))
    private Trayecto trayecto;

    @ManyToOne
    @JoinColumn(name = "medio_de_transporte", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Tramos_MedioDeTransporte"))
    private MedioDeTransporte medioDeTransporte;

    @ManyToOne
    @JoinColumn(name = "lugar_inicio", nullable = false, foreignKey = @ForeignKey(name = "FK_Tramos_LugarInicio"))
    private Lugar lugarInicio;

    @ManyToOne
    @JoinColumn(name = "lugar_fin", nullable = false, foreignKey = @ForeignKey(name = "FK_Tramos_LugarFin"))
    private Lugar lugarFin;

    private Double peso;

    @ManyToMany
    @JoinTable(
            name = "miembros_por_tramo",
            joinColumns = @JoinColumn(name = "tramo"),
            inverseJoinColumns = @JoinColumn(name = "miembro"),
            foreignKey = @ForeignKey(name = "FK_MiembrosPorTramo_Tramo"),
            inverseForeignKey = @ForeignKey(name = "FK_MiembrosPorTramo_Miembro")
    )
    private List<Miembro> miembros = new ArrayList<>();

    // Desnormaliza el dato de la distancia recorrida en el tramo, una vez que se calcula no tiene sentido volver
    // a calcularlo asi que se guarda aca.
    private Double distanciaRecorrida;

    public Tramo(Trayecto trayecto, MedioDeTransporte medioDeTransporte, Lugar lugarInicio, Lugar lugarFin) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.addTramo(this);
    }

    public void calcularDistanciaRecorrida(CalculadoraDistancias calculadoraDistancias) {
        if (medioDeTransporte instanceof TransportePublico) {
            TransportePublico transportePublico = (TransportePublico) medioDeTransporte;
            Parada paradaInicial = (Parada) lugarInicio;
            Parada paradaFinal = (Parada) lugarFin;
            distanciaRecorrida = calculadoraDistancias.calcularDistanciaTransportePublico(paradaInicial, paradaFinal);
        } else {
            distanciaRecorrida = calculadoraDistancias.calcularDistanciaConGeoService(lugarInicio, lugarFin);
        }
    }

    public double combustibleConsumido() {
        return distanciaRecorrida * medioDeTransporte.getCombustibleConsumidoPorKm();
    }

}
