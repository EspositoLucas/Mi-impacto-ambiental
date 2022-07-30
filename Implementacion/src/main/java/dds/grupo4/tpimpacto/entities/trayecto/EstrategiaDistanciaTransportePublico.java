package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;
import dds.grupo4.tpimpacto.services.calculodistancias.CalculadoraDistanciasTransportePublico;

public class EstrategiaDistanciaTransportePublico extends EstrategiaDistancia{

        private CalculadoraDistanciasTransportePublico distanciaTransportePublico ;

    @Override
    public double calcularDistanciaRecorrida(Lugar lugarInicial, Lugar lugarFinal) {
        return distanciaTransportePublico.calcularDistanciaRecorrida(lugarInicial,lugarFinal);
    }
}
