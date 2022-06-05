package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Parada")
@Table(name = "paradas")
public class Parada extends Lugar {

    private TransportePublico transportePublico;
    private Double distanciaProxima;
    private Double distanciaAnterior;
    private Parada paradaSiguiente;

    // Hibernate
    protected Parada() {
    }

    public Parada(TransportePublico transportePublico, Direccion direccion, Double distanciaProxima, Parada paradaSiguiente) {
        super(direccion);
        this.transportePublico = transportePublico;
        this.distanciaProxima = distanciaProxima;
        this.paradaSiguiente = paradaSiguiente;
    }

    public TransportePublico getTransportePublico() {
        return transportePublico;
    }

    public void setTransportePublico(TransportePublico transportePublico) {
        this.transportePublico = transportePublico;
    }

    public Double getDistanciaProxima() {
        return distanciaProxima;
    }

    public void setDistanciaProxima(Double distanciaProxima) {
        this.distanciaProxima = distanciaProxima;
    }

    public Parada getParadaSiguiente() {
        return paradaSiguiente;
    }

    public void setParadaSiguiente(Parada paradaSiguiente) {
        this.paradaSiguiente = paradaSiguiente;
    }

}
