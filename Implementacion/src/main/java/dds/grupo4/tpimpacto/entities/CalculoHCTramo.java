package dds.grupo4.tpimpacto.entities;

import java.util.ArrayList;
import java.util.List;

public class CalculoHCTramo {

    private List<Tramo> tramos = new ArrayList<>();

    public CalculoHCTramo(List<Tramo> tramos) {
        this.tramos = tramos;
    }


    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }


    // calculo para HC

//    public double calculoHCTramos() {
//    return this.tramos().stream()
//                .mapToDouble(t -> t.calculoHC())
//                .sum();;
//    }
}
