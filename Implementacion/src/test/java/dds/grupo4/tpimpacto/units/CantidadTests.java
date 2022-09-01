package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@CustomTestAnnotation
@FastTests
public class CantidadTests {

    @Test
    public void toUnidadBase_cuandoLaUnidadYaEsBase_noModificaNada() {
        Cantidad cantidad = new Cantidad(Unidad.KM, 10);
        Cantidad cantidadEnBase = cantidad.toUnidadBase();
        Assertions.assertEquals(10, cantidadEnBase.getValor());
        Assertions.assertEquals(Unidad.KM, cantidadEnBase.getUnidad());
    }

    @Test
    public void toUnidadBase_cuandoLaUnidadNoEsBase_retornaCantidadConvertidaAUnidadBase() {
        Cantidad cantidad = new Cantidad(Unidad.M, 1000);
        Cantidad cantidadEnBase = cantidad.toUnidadBase();
        Assertions.assertEquals(1, cantidadEnBase.getValor());
        Assertions.assertEquals(Unidad.KM, cantidadEnBase.getUnidad());
    }

    @Test
    public void toUnidad_cuandoLaNuevaUnidadEsLaMisma_noModificaNada() {
        Cantidad cantidad = new Cantidad(Unidad.M, 1);
        Cantidad cantidadConvertida = cantidad.toUnidad(Unidad.M);
        Assertions.assertEquals(1, cantidadConvertida.getValor());
        Assertions.assertEquals(Unidad.M, cantidadConvertida.getUnidad());
    }

    @Test
    public void toUnidad_cuandoLaNuevaUnidadEsBase_retornaValoresConvertidosABase() {
        Cantidad cantidad = new Cantidad(Unidad.M, 1000);
        Cantidad cantidadConvertida = cantidad.toUnidad(Unidad.KM);

        Assertions.assertEquals(1, cantidadConvertida.getValor());
        Assertions.assertEquals(Unidad.KM, cantidadConvertida.getUnidad());
    }

    @Test
    public void toUnidad_cuandoNingunaUnidadInvolucradaEsBase_retornaValoresConvertidos() {
        Cantidad cantidad = new Cantidad(Unidad.GCO2eq, 1_000_000);
        Cantidad cantidadConvertida = cantidad.toUnidad(Unidad.TNCO2eq);

        Assertions.assertEquals(1, cantidadConvertida.getValor());
        Assertions.assertEquals(Unidad.TNCO2eq, cantidadConvertida.getUnidad());
    }

    @Test
    public void add_cuandoLasUnidadesSonIguales_retornaSumaDeValores() {
        Cantidad left = new Cantidad(Unidad.M, 1);
        Cantidad right = new Cantidad(Unidad.M, 1);
        Cantidad resultado = left.add(right);

        Assertions.assertEquals(2, resultado.getValor());
        Assertions.assertEquals(Unidad.M, resultado.getUnidad());
    }

    @Test
    public void add_cuandoLasUnidadesSonDiferentes_retornaSumaCorrectaConUnidadesDeLaIzquierda() {
        Cantidad left = new Cantidad(Unidad.M, 1000);
        Cantidad right = new Cantidad(Unidad.KM, 1);
        Cantidad resultado = left.add(right);

        Assertions.assertEquals(2000, resultado.getValor());
        Assertions.assertEquals(Unidad.M, resultado.getUnidad());
    }

    @Test
    public void add_cuandoLasUnidadesSonDeDistintasCategoriasDiferentes_tiraExcepcion() {
        Cantidad left = new Cantidad(Unidad.M, 1);
        Cantidad right = new Cantidad(Unidad.KG, 1);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> left.add(right)
        );
    }
}
