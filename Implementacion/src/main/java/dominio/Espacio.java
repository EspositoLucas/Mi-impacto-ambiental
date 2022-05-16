package dominio;

public class Espacio {

    private Direccion partida ;
    private Direccion destino ;
    private Direccion ubicacionGeografica;

    public Espacio(Direccion partida, Direccion destino, Direccion ubicacionGeografica) {
        this.partida = partida;
        this.destino = destino;
        this.ubicacionGeografica = ubicacionGeografica;
    }
}
