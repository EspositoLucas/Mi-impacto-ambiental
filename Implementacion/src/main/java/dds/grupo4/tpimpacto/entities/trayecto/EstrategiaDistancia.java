package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;

// FIX: para qué está esta clase?
public class EstrategiaDistancia {
    private MedioDeTransporte medioDeTransporte;

    public Double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        return medioDeTransporte.distanciaRecorrida(lugarInicio, lugarFin);
    }
}
