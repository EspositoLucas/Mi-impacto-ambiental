package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Parada")
@Table(name = "paradas")
public class Parada extends Lugar {

    private TransportePublico transportePublico;
    private Direccion direccion;
    private Double distanciaProxima;
    private Double distanciaAnterior;
    private Parada paradaSiguiente;

    // Hibernate
    protected Parada() {
    }

    public Parada(TransportePublico transportePublico, Direccion direccion, Parada paradaSiguiente) {
        this.transportePublico = transportePublico;
        this.direccion = direccion;
        this.paradaSiguiente = paradaSiguiente;
    }

    public TransportePublico getTransportePublico() {
        return transportePublico;
    }

    public void setTransportePublico(TransportePublico transportePublico) {
        this.transportePublico = transportePublico;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public double getDistanciaProxima() { return distanciaProxima; }

    public Parada getParadaSiguiente() {
        return paradaSiguiente;
    }

}
