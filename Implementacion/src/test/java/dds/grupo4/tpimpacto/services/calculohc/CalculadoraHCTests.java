package dds.grupo4.tpimpacto.services.calculohc;

import dds.grupo4.tpimpacto.common.DateTimeService;
import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import dds.grupo4.tpimpacto.entities.medicion.*;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;
import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.TipoOrganizacion;
import dds.grupo4.tpimpacto.services.RelacionUnidadesService;
import dds.grupo4.tpimpacto.services.TipoConsumoService;
import dds.grupo4.tpimpacto.services.UnidadService;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@CustomTestAnnotation
@FastTests
public class CalculadoraHCTests {

    private final UnidadService unidadService;
    private final RelacionUnidadesService relacionUnidadesService;
    private final TipoConsumoService tipoConsumoService;

    private CalculadoraHC calculadoraHC;

    @Mock
    private DateTimeService dateTimeService;

    private Unidad KM;
    private Unidad GCO2eq_SOBRE_KM;
    private Unidad GCO2eq;
    private Unidad UNO_SOBRE_KG;
    private TipoConsumo tipoConsumoParaNoLogistica;
    private TipoConsumo tipoConsumoPesoTotalTransportado;
    private TipoConsumo tipoConsumoDistanciaMediaRecorrida;
    private TipoConsumo tipoConsumoMedioDeTransporte;
    private Organizacion organizacion;


    @Autowired
    public CalculadoraHCTests(UnidadService unidadService, RelacionUnidadesService relacionUnidadesService, TipoConsumoService tipoConsumoService) {
        this.unidadService = unidadService;
        this.relacionUnidadesService = relacionUnidadesService;
        this.tipoConsumoService = tipoConsumoService;
    }

    @BeforeAll
    public void setup() {
        KM = unidadService.getBySimbolo("km").get();
        GCO2eq_SOBRE_KM = unidadService.getBySimbolo("gCO2eq/km").get();
        GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        UNO_SOBRE_KG = unidadService.getBySimbolo("1/kg").get();

        tipoConsumoPesoTotalTransportado = tipoConsumoService.getByNombre("Peso total transportado").get();
        tipoConsumoDistanciaMediaRecorrida = tipoConsumoService.getByNombre("Distancia media recorrida").get();
        tipoConsumoMedioDeTransporte = tipoConsumoService.getByNombre("Medio de transporte").get();
    }

    @BeforeEach
    public void forEachSetup() {
        calculadoraHC = new CalculadoraHC(relacionUnidadesService, dateTimeService);

        tipoConsumoParaNoLogistica = new TipoConsumo("TipoConsumo para no logistica", Actividad.COMBUSTION_FIJA,
                "AlcanceTest", KM);
        FactorDeEmision factorDeEmisionParaTipoConsumo = new FactorDeEmision(
                new Cantidad(GCO2eq_SOBRE_KM, 10)
        );
        tipoConsumoParaNoLogistica.setFactorDeEmision(factorDeEmisionParaTipoConsumo);

        organizacion = new Organizacion("OrganizacionTest", TipoOrganizacion.EMPRESA,
                Clasificacion.EMPRESA_SECTOR_PRIMARIO,
                new Cantidad(UNO_SOBRE_KG, 2),
                5
        );

    }

    @Test
    @Transactional
    public void calcularHCDatoActividadNoLogistica_haceCalculoCorrectoParaNoLogistica() {
        // HC = DatoActividad * FE
        Medicion medicion = new Medicion(Actividad.COMBUSTION_FIJA, tipoConsumoParaNoLogistica,
                Periodicidad.MENSUAL, "01/2022", "2");
        Cantidad hc = calculadoraHC.calcularHCDatoActividadNoLogistica(medicion);

        Assertions.assertEquals(GCO2eq, hc.getUnidad());
        Assertions.assertEquals(20, hc.getValor());
    }

    @Test
    @Transactional
    public void calcularHCDatoActividadLogistica_haceCalculoCorrectoParaLogistica() {
        // HC = Peso * K * Distancia * FE
        Medicion medicionPesoTotalTransportado = new Medicion(
                Actividad.COMBUSTION_FIJA,
                tipoConsumoPesoTotalTransportado,
                Periodicidad.MENSUAL,
                "01/2022",
                "5"
        );
        medicionPesoTotalTransportado.setOrganizacion(organizacion);
        Medicion medicionDistanciaMediaRecorrida = new Medicion(
                Actividad.COMBUSTION_FIJA,
                tipoConsumoDistanciaMediaRecorrida,
                Periodicidad.MENSUAL,
                "01/2022",
                "10"
        );
        medicionDistanciaMediaRecorrida.setOrganizacion(organizacion);

        MedioDeTransporte medioDeTransporte = new TransportePublico(null, "LineaTest", null, 0);
        medioDeTransporte.setFactorDeEmision(new FactorDeEmision(new Cantidad(GCO2eq_SOBRE_KM, 20)));
        Medicion medicionMedioDeTransporte = new Medicion(
                Actividad.COMBUSTION_FIJA,
                tipoConsumoMedioDeTransporte,
                Periodicidad.MENSUAL,
                "01/2022",
                ""
        );
        medicionMedioDeTransporte.setMedioDeTransporte(medioDeTransporte);
        medicionMedioDeTransporte.setOrganizacion(organizacion);

        List<Medicion> medicionesLogistica = Arrays.asList(medicionPesoTotalTransportado, medicionDistanciaMediaRecorrida, medicionMedioDeTransporte);
        Cantidad hc = calculadoraHC.calcularHCDatoActividadLogistica(medicionesLogistica);

        Assertions.assertEquals(GCO2eq, hc.getUnidad());
        Assertions.assertEquals(5d * 2 * 10 * 20, hc.getValor());
    }

    @Test
    @Transactional
    public void calcularHCAnualProrrateadoDatoActividad_cuandoElPeriodoImputacionEsElAnioActual_prorrateaALosMesesAnteriores() {
        when(dateTimeService.getCurrentDate()).thenReturn(LocalDate.of(2022, 6, 1));
        Cantidad valorHC = new Cantidad(GCO2eq, 10);

        Cantidad valorHCProrrateado = calculadoraHC.calcularHCAnualProrrateadoDatoActividad(valorHC, 2022);

        Assertions.assertEquals(GCO2eq, valorHCProrrateado.getUnidad());
        Assertions.assertEquals(10d / 5, valorHCProrrateado.getValor());
    }

    @Test
    @Transactional
    public void calcularHCAnualProrrateadoDatoActividad_cuandoElPeriodoImputacionEsUnAnioAnterior_prorratea12Meses() {
        when(dateTimeService.getCurrentDate()).thenReturn(LocalDate.of(2022, 6, 1));
        Cantidad valorHC = new Cantidad(GCO2eq, 24);

        Cantidad valorHCProrrateado = calculadoraHC.calcularHCAnualProrrateadoDatoActividad(valorHC, 2021);

        Assertions.assertEquals(GCO2eq, valorHCProrrateado.getUnidad());
        Assertions.assertEquals(24d / 12, valorHCProrrateado.getValor());
    }
}
