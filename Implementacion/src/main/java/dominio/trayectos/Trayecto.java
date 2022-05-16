package dominio.trayectos;

import dominio.Direccion;

import java.util.List;

public class Trayecto {

    private Direccion partida ;
    private Direccion destino ;
    private List<Tramo> tramos;

    public Trayecto(Direccion partida, Direccion destino, List<Tramo> tramos) {
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
}
