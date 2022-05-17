package dds.grupo4.tpimpacto.entities;

public class Parada {

    private TransportePublico transportePublico;
    private Direccion direccion;

    public Parada(TransportePublico trasnportePublico, Direccion direccion) {
        this.transportePublico = trasnportePublico;
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
