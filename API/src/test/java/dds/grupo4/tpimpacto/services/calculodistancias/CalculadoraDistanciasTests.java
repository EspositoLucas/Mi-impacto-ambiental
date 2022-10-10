package dds.grupo4.tpimpacto.services.calculodistancias;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.services.UnidadService;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.when;

@CustomTestAnnotation
@FastTests
public class CalculadoraDistanciasTests {

    @Mock
    private UnidadService unidadService;
    private Unidad KM;

    private CalculadoraDistancias calculadoraDistancias;

    @Autowired
    public CalculadoraDistanciasTests(CalculadoraDistancias calculadoraDistancias) {
        this.calculadoraDistancias = calculadoraDistancias;
    }

    @BeforeAll
    public void setup() {
        KM = new Unidad("km", "Kilometro", true, 1);
        when(unidadService.getBySimbolo("km")).thenReturn(Optional.of(KM));
        calculadoraDistancias = new CalculadoraDistancias(null, unidadService);
    }

    @Test
    public void calcularDistanciaTransportePublico_cuandoParadaInicialEsIgualAFinal_retorna0() {
        Parada parada = new Parada(null, null);
        Cantidad distancia = calculadoraDistancias.calcularDistanciaTransportePublico(parada, parada);
        Assertions.assertEquals(0, distancia.getValor());
    }

    @Test
    public void calcularDistanciaTransportePublico_cuandoHayDosParadas_retornaSuDistancia() {
        Cantidad distanciaParadaSiguiente = new Cantidad(KM, 5);
        Parada parada1 = new Parada(null, distanciaParadaSiguiente);
        Parada parada2 = new Parada(null, null);
        parada2.setId(2);

        parada1.setParadaSiguiente(parada2);

        Cantidad distancia = calculadoraDistancias.calcularDistanciaTransportePublico(parada1, parada2);
        Assertions.assertEquals(5, distancia.getValor());
    }

    @Test
    public void calcularDistanciaTransportePublico_cuandoHayCadenaDeParadas_retornaSumaDeDistancias() {
        /* Distancias:
         *  - De 1 a 2: 5m
         *  - De 2 a 3: 10m
         *  - De 3 a 4: 15m
         * Total: 30m
         */
        Cantidad distanciaParadaSiguiente1 = new Cantidad(KM, 5);
        Parada parada1 = new Parada(null, distanciaParadaSiguiente1);
        parada1.setId(1);
        Cantidad distanciaParadaSiguiente2 = new Cantidad(KM, 10);
        Parada parada2 = new Parada(null, distanciaParadaSiguiente2);
        parada2.setId(2);
        Cantidad distanciaParadaSiguiente3 = new Cantidad(KM, 15);
        Parada parada3 = new Parada(null, distanciaParadaSiguiente3);
        parada3.setId(3);
        Parada parada4 = new Parada(null, null);
        parada3.setId(4);

        parada1.setParadaSiguiente(parada2);
        parada2.setParadaSiguiente(parada3);
        parada3.setParadaSiguiente(parada4);

        Cantidad distancia = calculadoraDistancias.calcularDistanciaTransportePublico(parada1, parada4);
        Assertions.assertEquals(30, distancia.getValor());
    }

}
