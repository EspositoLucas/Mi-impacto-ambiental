package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;

public class EstrategiaDistancia {
    private MedioDeTransporte mediotrasnporte ;

    public Double distancia(Lugar lugarInicio, Lugar lugarFin) {
        return this.mediotrasnporte.distanciaRecorrida(lugarInicio,lugarFin);
    }
}
