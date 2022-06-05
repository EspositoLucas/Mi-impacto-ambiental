package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Trayecto")
@Table(name = "trayectos")
public class Trayecto extends BaseEntity {

    private Direccion partida;
    private Direccion destino;
    private List<Tramo> tramos = new ArrayList<>();


    // Hibernate
    protected Trayecto() {
    }

    public Trayecto(Direccion partida, Direccion destino, List<Tramo> tramos, List<Miembro> miembros) {
        this.partida = partida;
        this.destino = destino;
        this.tramos = tramos;
    }

    public Direccion getPartida() {
        return partida;
    }

    public void setPartida(Direccion partida) {
        this.partida = partida;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    //Metodo para calcular la distancia total de un trayecto
    public double distanciaTotal() {
        return this.tramos.stream()
                .mapToDouble(t -> t.distancia())
                .sum();
    }


//     cant de litros de combustible consumidos por medio de trasnporte en un trayecto a partir  de lo
//     que se consumio en un tramo
//
//    public double cantlitrosConsumidos() {
//        this.tramos
//                .stream()
//                .mapToDouble(t -> t.litrosConsumidos())
//                .sum();
//    }


}
