package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoTransportePublico;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TransportePublico")
@Table(name = "transportes_publicos")
public class TransportePublico extends MedioDeTransporte {

    private TipoTransportePublico tipoTransportePublico;
    private String linea;
    private List<Parada> paradas = new ArrayList<>();
    private Combustible combustible;

    // Hibernate
    protected TransportePublico() {
    }

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Parada> paradas, Combustible combustible) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.linea = linea;
        paradas.forEach(parada -> addParada(parada));
        this.combustible = combustible;
    }

    public TipoTransportePublico getTipoTransportePublico() {
        return tipoTransportePublico;
    }

    public void setTipoTransportePublico(TipoTransportePublico tipoTransportePublico) {
        this.tipoTransportePublico = tipoTransportePublico;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public void addParada(Parada parada) {
        paradas.add(parada);
        parada.setTransportePublico(this);
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        Parada paradaInicio = (Parada) lugarInicio;
        Parada paradaFin = (Parada) lugarInicio;

        double distanciaRecorrida = 0;
        Parada paradaActual = paradaInicio;
        while (!paradaActual.equals(paradaFin)) {
            distanciaRecorrida += paradaActual.getDistanciaProxima();
            paradaActual = paradaActual.getParadaSiguiente();
        }

        return distanciaRecorrida;
    }

}
