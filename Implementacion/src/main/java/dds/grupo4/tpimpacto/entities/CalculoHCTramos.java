package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalculoHCTramos {

    private List<Tramo> tramos = new ArrayList<>();

    public CalculoHCTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    // calculo para HC

    public double calculoHCTramos() {
    return this.tramos.stream().mapToDouble(t-> t.calculoHC()).sum();
    }
}
