package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.GeoServiceImpl;

public class EstrategiaDistanciaGeoService  extends EstrategiaDistancia{

        private GeoServiceImpl distanciaGeoService ;

    @Override
    public double calcularDistanciaRecorrida(Lugar lugarInicial, Lugar lugarFinal) {
        return distanciaGeoService.calcularDistanciaRecorrida(lugarInicial,lugarFinal);
    }
}
