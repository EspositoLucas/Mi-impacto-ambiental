package dds.grupo4.tpimpacto.entities;

import java.util.ArrayList;
import java.util.List;

public class CalculoHCActividad {

    private List<Medicion> mediciones = new ArrayList<>();

    public CalculoHCActividad(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }

    public List<Medicion> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<Medicion> mediciones) {
        this.mediciones = mediciones;
    }


// calculo para HC

//    public double calculoHCActividad() {
//    return ;
//    }
}
