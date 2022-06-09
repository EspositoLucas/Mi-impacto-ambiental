package dds.grupo4.tpimpacto.services.apiSwagger.entities;

public class Distancia {

    public float valor ;
    public String unidad ;
    public Parametro parametros;

    private class Parametro {
        public int localidadOrigenId;
        public String  calleOrigen;
        public int alturaOrigen;
        public int localidadDestinoId;
        public String  calleDestino;
        public int alturaDestino;

    }


}

