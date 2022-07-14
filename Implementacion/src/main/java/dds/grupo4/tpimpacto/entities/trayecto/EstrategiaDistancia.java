package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;

public class EstrategiaDistancia {
    private MedioDeTransporte mediotrasnporte ;

    public Double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        return this.mediotrasnporte.distanciaRecorrida(lugarInicio,lugarFin);
    }
}
