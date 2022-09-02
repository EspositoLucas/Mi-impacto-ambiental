package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@CustomTestAnnotation
@FastTests
public class CantidadTests {

    private Unidad KG;
    private Unidad G;
    private Unidad TN;
    private Unidad M;

    @BeforeEach
    public void setup() {
        TipoUnidad masa = new TipoUnidad("Masa");
        masa.setId(1);
        KG = new Unidad("kg", "Kilogramos", true, 1d);
        KG.setId(2);
        G = new Unidad("g", "Gramos", false, 1d / 1000);
        G.setId(3);
        TN = new Unidad("tn", "Toneladas", false, 1000d);
        TN.setId(4);
        masa.addUnidad(KG);
        masa.addUnidad(G);
        masa.addUnidad(TN);

        TipoUnidad distancia = new TipoUnidad("Distancia");
        distancia.setId(5);
        M = new Unidad("m", "Metros", false, 1d / 1000);
        M.setId(6);
        distancia.addUnidad(M);
    }

    @Test
    public void toUnidadBase_cuandoLaUnidadYaEsBase_noModificaNada() {
        Cantidad cantidad = new Cantidad(KG, 1);
        Cantidad cantidadEnBase = cantidad.toUnidadBase();
        Assertions.assertEquals(1, cantidadEnBase.getValor());
        Assertions.assertEquals(KG, cantidadEnBase.getUnidad());
    }

    @Test
    public void toUnidadBase_cuandoLaUnidadNoEsBase_retornaCantidadConvertidaAUnidadBase() {
        Cantidad cantidad = new Cantidad(G, 1000);
        Cantidad cantidadEnBase = cantidad.toUnidadBase();
        Assertions.assertEquals(1, cantidadEnBase.getValor());
        Assertions.assertEquals(KG, cantidadEnBase.getUnidad());
    }

    @Test
    public void toUnidad_cuandoLaNuevaUnidadEsLaMisma_noModificaNada() {
        Cantidad cantidad = new Cantidad(G, 1);
        Cantidad cantidadConvertida = cantidad.toUnidad(G);
        Assertions.assertEquals(1, cantidadConvertida.getValor());
        Assertions.assertEquals(G, cantidadConvertida.getUnidad());
    }

    @Test
    public void toUnidad_cuandoLaNuevaUnidadEsBase_retornaValoresConvertidosABase() {
        Cantidad cantidad = new Cantidad(G, 1000);
        Cantidad cantidadConvertida = cantidad.toUnidad(KG);

        Assertions.assertEquals(1, cantidadConvertida.getValor());
        Assertions.assertEquals(KG, cantidadConvertida.getUnidad());
    }

    @Test
    public void toUnidad_cuandoNingunaUnidadInvolucradaEsBase_retornaValoresConvertidos() {
        Cantidad cantidad = new Cantidad(G, 1_000_000);
        Cantidad cantidadConvertida = cantidad.toUnidad(TN);

        Assertions.assertEquals(1, cantidadConvertida.getValor());
        Assertions.assertEquals(TN, cantidadConvertida.getUnidad());
    }

    @Test
    public void add_cuandoLasUnidadesSonIguales_retornaSumaDeValores() {
        Cantidad left = new Cantidad(G, 1);
        Cantidad right = new Cantidad(G, 1);
        Cantidad resultado = left.add(right);

        Assertions.assertEquals(2, resultado.getValor());
        Assertions.assertEquals(G, resultado.getUnidad());
    }

    @Test
    public void add_cuandoLasUnidadesSonDiferentes_retornaSumaCorrectaConUnidadesDeLaIzquierda() {
        Cantidad left = new Cantidad(G, 1000);
        Cantidad right = new Cantidad(KG, 1);
        Cantidad resultado = left.add(right);

        Assertions.assertEquals(2000, resultado.getValor());
        Assertions.assertEquals(G, resultado.getUnidad());
    }

    @Test
    public void add_cuandoLasUnidadesSonDeDistintasCategoriasDiferentes_tiraExcepcion() {
        Cantidad left = new Cantidad(G, 1);
        Cantidad right = new Cantidad(M, 1);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> left.add(right)
        );
    }
}
