package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Parada")
@Table(name = "paradas")
public class Parada extends BaseEntity {

    private TransportePublico transportePublico;
    private Direccion direccion;

    // Hibernate
    protected Parada() {
    }

    public Parada(TransportePublico transportePublico, Direccion direccion) {
        this.transportePublico = transportePublico;
        this.direccion = direccion;
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
}
