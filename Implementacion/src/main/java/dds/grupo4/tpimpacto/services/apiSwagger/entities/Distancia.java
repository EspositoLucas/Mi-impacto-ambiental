package dds.grupo4.tpimpacto.services.apiSwagger.entities;

public class Distancia {

    public float valor;
    public String unidad;
    public ParametroDistancia parametros;

    private class ParametroDistancia {
        public int localidadOrigenId;
        public String calleOrigen;
        public String alturaOrigen;
        public int localidadDestinoId;
        public String calleDestino;
        public String alturaDestino;

    }


}

