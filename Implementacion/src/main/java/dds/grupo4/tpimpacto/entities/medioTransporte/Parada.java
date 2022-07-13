package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Parada")
@Table(name = "paradas")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parada extends Lugar {

    @ManyToOne
    @JoinColumn(name = "???transporte_publico???", nullable = false)
    private TransportePublico transportePublico;

    private Double distanciaProxima;
    private Double distanciaAnterior;

    // TODO: ver como se anota esto en Hibernate (referencia recursiva)
    private Parada paradaSiguiente;

    public Parada(Direccion direccion, Double distanciaProxima) {
        super(direccion);
        this.distanciaProxima = distanciaProxima;
    }

}
