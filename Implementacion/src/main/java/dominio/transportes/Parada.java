package dominio.transportes;

import dominio.Direccion;

public class Parada {

    private TransportePublico  trasnporte ;
    private Direccion direccion ;

    public Parada(TransportePublico trasnporte, Direccion direccion) {
        this.trasnporte = trasnporte;
        this.direccion = direccion;
    }
}
