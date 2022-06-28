package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoTransportePublico;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TransportePublico")
@Table(name = "transportes_publicos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransportePublico extends MedioDeTransporte {

    private TipoTransportePublico tipoTransportePublico;
    private String linea;
    private List<Parada> paradas = new ArrayList<>();
    private Combustible combustible;

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Parada> paradas, Combustible combustible) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.linea = linea;
        paradas.forEach(parada -> addParada(parada));
        this.combustible = combustible;
    }

    public void addParada(Parada parada) {
        paradas.add(parada);
        parada.setTransportePublico(this);
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        Parada paradaInicio = (Parada) lugarInicio;
        Parada paradaFin = (Parada) lugarFin;

        double distanciaRecorrida = 0;
        Parada paradaActual = paradaInicio;
        while (!paradaActual.equals(paradaFin)) {
            distanciaRecorrida += paradaActual.getDistanciaProxima();
            paradaActual = paradaActual.getParadaSiguiente();
        }

        return distanciaRecorrida;
    }

    //    @Override
//    public double cantConsumidaCombustible() {
//      return 0;
//    }

}
