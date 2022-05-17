package dds.grupo4.tpimpacto.entities;

public class Espacio {

    private Direccion ubicacionGeografica;

    public Espacio(Direccion ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

    public Direccion getUbicacionGeografica() {
        return ubicacionGeografica;
    }

    public void setUbicacionGeografica(Direccion ubicacionGeografica) {
        this.ubicacionGeografica = ubicacionGeografica;
    }

}
