package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import dds.grupo4.tpimpacto.repositories.RelacionUnidadesRepository;
import dds.grupo4.tpimpacto.services.RelacionUnidadesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@CustomTestAnnotation
@FastTests
public class CantidadTests {

    private Unidad KG;
    private Unidad G;
    private Unidad TN;
    private Unidad KM;
    private Unidad M;
    private Unidad GCO2eq;
    private Unidad GCO2eq_SOBRE_KG;
    private Unidad GCO2eq_SOBRE_KM;
    private Unidad UNO_SOBRE_KG;

    @Mock
    private RelacionUnidadesRepository relacionUnidadesRepository;

    private RelacionUnidadesService relacionUnidadesService;

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
        masa.addUnidades(Arrays.asList(KG, G, TN));

        TipoUnidad distancia = new TipoUnidad("Distancia");
        distancia.setId(5);
        KM = new Unidad("km", "Kilometros", true, 1d);
        KM.setId(100);
        M = new Unidad("m", "Metros", false, 1d / 1000);
        M.setId(6);
        distancia.addUnidades(Arrays.asList(KM, M));

        GCO2eq = new Unidad("gCO2eq", "Gramo equivalente carbono", true, 1);
        GCO2eq.setId(7);
        GCO2eq_SOBRE_KG = new Unidad("gCO2eq/kg", "Gramo equivalente carbono sobre Kilogramo", true, 1);
        GCO2eq_SOBRE_KG.setId(8);
        GCO2eq_SOBRE_KM = new Unidad("gCO2eq/km", "Gramo equivalente carbono sobre Kilometro", true, 1);
        GCO2eq_SOBRE_KM.setId(101);
        UNO_SOBRE_KG = new Unidad("1/kg", "1 sobre Kilogramo", true, 1);
        UNO_SOBRE_KG.setId(9);

        RelacionUnidades relacion_GCO2eq_KG = new RelacionUnidades(GCO2eq, KG, null, GCO2eq_SOBRE_KG);
        relacion_GCO2eq_KG.setId(10);
        RelacionUnidades relacion_KG_UNOSOBREKG = new RelacionUnidades(null, KG, KG, UNO_SOBRE_KG);
        relacion_KG_UNOSOBREKG.setId(11);
        RelacionUnidades relacion_GCO2eq_KM = new RelacionUnidades(GCO2eq, KM, null, GCO2eq_SOBRE_KM);
        relacion_GCO2eq_KM.setId(102);

        relacionUnidadesService = new RelacionUnidadesService(relacionUnidadesRepository);
        when(relacionUnidadesRepository.getByUnidades(isNull(), eq(KG), isNull(), eq(GCO2eq_SOBRE_KG))).thenReturn(Optional.of(relacion_GCO2eq_KG));
        when(relacionUnidadesRepository.getByUnidades(isNull(), eq(KM), isNull(), eq(GCO2eq_SOBRE_KM))).thenReturn(Optional.of(relacion_GCO2eq_KM));
        when(relacionUnidadesRepository.getByUnidades(isNull(), eq(KG), isNull(), eq(UNO_SOBRE_KG))).thenReturn(Optional.of(relacion_KG_UNOSOBREKG));
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

    @Test
    public void add_cuandoUnaCantidadNoTieneUnidadesYLaOtraSi_tiraExcepcion() {
        Cantidad cantidadConUnidades = new Cantidad(KG, 1);
        Cantidad cantidadSinUnidades = new Cantidad(null, 2);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> cantidadConUnidades.add(cantidadSinUnidades)
        );
    }

    @Test
    public void times_cuandoLaUnidadDerechaSeRelacionaConLaUnidadCociente_retornaUnidadIzquierda() {
        Cantidad left = new Cantidad(KG, 5);
        Cantidad right = new Cantidad(GCO2eq_SOBRE_KG, 10);
        Cantidad resultado = left.times(right, relacionUnidadesService);

        Assertions.assertEquals(50, resultado.getValor());
        Assertions.assertEquals(GCO2eq, resultado.getUnidad());
    }

    @Test
    public void times_cuandoElProductoCancelaUnidades_retornaCantidadSinUnidad() {
        Cantidad left = new Cantidad(KG, 5);
        Cantidad right = new Cantidad(UNO_SOBRE_KG, 10);
        Cantidad resultado = left.times(right, relacionUnidadesService);

        Assertions.assertEquals(50, resultado.getValor());
        Assertions.assertNull(resultado.getUnidad());
    }

    @Test
    public void times_cuandoElProductoEsEncadenado_retornaCantidadCorrecta() {
        /*
         * El HC para Logistica es HC = Peso * K * Distancia * FE, donde las unidades son:
         *  - [Peso] = KG
         *  - [K] = 1/KG
         *  - [Distancia] = KM
         *  - [FE] = GCO2eq/KM
         *  - [HC] = GCO2eq
         */

        Cantidad peso = new Cantidad(KG, 5);
        Cantidad K = new Cantidad(UNO_SOBRE_KG, 2);
        Cantidad distancia = new Cantidad(KM, 2);
        Cantidad FE = new Cantidad(GCO2eq_SOBRE_KM, 2);
        Cantidad resultado = peso.times(K, relacionUnidadesService)
                .times(distancia, relacionUnidadesService)
                .times(FE, relacionUnidadesService);

        Assertions.assertEquals(40, resultado.getValor());
        Assertions.assertEquals(GCO2eq, resultado.getUnidad());
    }

    @Test
    public void times_cuandoUnaUnidadNoEsBase_retornaUnidadCorrectaConConversionDeValor() {
        // 1000g == 1kg, y 1kg*2(1/kg) == 2 (se cancelan las unidades)
        Cantidad left = new Cantidad(G, 1000);
        Cantidad right = new Cantidad(UNO_SOBRE_KG, 2);
        Cantidad resultado = left.times(right, relacionUnidadesService);

        Assertions.assertEquals(2, resultado.getValor());
        Assertions.assertNull(resultado.getUnidad());
    }

    @Test
    public void times_cuandoElProductoEsPorUnFactorNumerico_retornaCantidadConMismaUnidad() {
        Cantidad cantidad = new Cantidad(KG, 5);
        Cantidad resultado = cantidad.times(2);

        Assertions.assertEquals(10, resultado.getValor());
        Assertions.assertEquals(KG, resultado.getUnidad());
    }

    @Test
    public void divide_cuandoSeDividePorUnFactorNumerico_retornaCantidadCorrectaConMismaUnidad() {
        Cantidad cantidad = new Cantidad(KG, 10);
        Cantidad resultado = cantidad.divide(10);

        Assertions.assertEquals(1, resultado.getValor());
        Assertions.assertEquals(KG, resultado.getUnidad());
    }
}
