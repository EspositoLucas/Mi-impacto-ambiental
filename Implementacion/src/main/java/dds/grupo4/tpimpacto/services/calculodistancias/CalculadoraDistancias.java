package dds.grupo4.tpimpacto.services.calculodistancias;

import dds.grupo4.tpimpacto.entities.trayecto.Lugar;

public interface CalculadoraDistancias {
    double calcularDistanciaRecorrida(Lugar lugarInicial, Lugar lugarFinal);
}
