package dds.grupo4.tpimpacto.services.calculodistancias;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@CustomTestAnnotation
@FastTests
public class CalculadoraDistanciasTests {

    private final CalculadoraDistancias calculadoraDistancias;

    @Autowired
    public CalculadoraDistanciasTests(CalculadoraDistancias calculadoraDistancias) {
        this.calculadoraDistancias = calculadoraDistancias;
    }

    @Test
    public void calcularDistanciaTransportePublico_cuandoParadaInicialEsIgualAFinal_retorna0() {
        Parada parada = new Parada(null, 0d);
        double distancia = calculadoraDistancias.calcularDistanciaTransportePublico(parada, parada);
        Assertions.assertEquals(0, distancia);
    }

    @Test
    public void calcularDistanciaTransportePublico_cuandoHayDosParadas_retornaSuDistancia() {
        Parada parada1 = new Parada(null, 5d);
        parada1.setId(1);
        Parada parada2 = new Parada(null, null);
        parada2.setId(2);

        parada1.setParadaSiguiente(parada2);

        double distancia = calculadoraDistancias.calcularDistanciaTransportePublico(parada1, parada2);
        Assertions.assertEquals(5d, distancia);
    }

    @Test
    public void calcularDistanciaTransportePublico_cuandoHayCadenaDeParadas_retornaSumaDeDistancias() {
        /* Distancias:
         *  - De 1 a 2: 5m
         *  - De 2 a 3: 10m
         *  - De 3 a 4: 15m
         * Total: 30m
         */
        Parada parada1 = new Parada(null, 5d);
        parada1.setId(1);
        Parada parada2 = new Parada(null, 10d);
        parada2.setId(2);
        Parada parada3 = new Parada(null, 15d);
        parada3.setId(3);
        Parada parada4 = new Parada(null, null);
        parada3.setId(4);

        parada1.setParadaSiguiente(parada2);
        parada2.setParadaSiguiente(parada3);
        parada3.setParadaSiguiente(parada4);

        double distancia = calculadoraDistancias.calcularDistanciaTransportePublico(parada1, parada4);
        Assertions.assertEquals(30d, distancia);
    }

}
