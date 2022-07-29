package dds.grupo4.tpimpacto.services.calculodistancias;

import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;

public class CalculadoraDistanciasTransportePublico implements CalculadoraDistancias {
    @Override
    public double calcularDistanciaRecorrida(Lugar lugarInicial, Lugar lugarFinal) {
        Parada paradaInicial = (Parada) lugarInicial;
        Parada paradaFinal = (Parada) lugarFinal;

        double distanciaRecorrida = 0;
        Parada paradaActual = paradaInicial;
        while (!paradaActual.equals(paradaFinal)) {
            distanciaRecorrida += paradaActual.getDistanciaParadaSiguiente();
            paradaActual = paradaActual.getParadaSiguiente();
        }

        return distanciaRecorrida;
    }
}
