package dds.grupo4.tpimpacto.common;

import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.services.OrganizacionServiceTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;


@Disabled
public class TestLecturaExcel extends OrganizacionServiceTests {

    private Medicion medicion0;
    private Medicion medicion1;


    @BeforeEach
    void inicializarDatosDeActividades() {

//    setUp();
//    this.organizacionTest.cargarDatosDeActividades("/resources/DA.csv"); // POR QUÉ AHORA NO ABRE????
//
//    medicion0 = organizacion.getDatosDeActividades().get(0);
//    medicion1 = organizacion.getDatosDeActividades().get(1);

    }
    // así lee el excel:
    // [["gas_natural" "12" "mensual" "12/2022"] ["nafta" "1.4" "anual" "2011"] ["este_tira_errores" "aaaa" "aaaa" "aaaa"]]

    // PRIMER DATO
/*
  @Test
  public void coincideElConsumoDelPrimerDato() {
    Assertions.assertEquals(RepositorioConsumos.tipoDeConsumo("gas_natural"), tipoDeConsumo0.getConsumo());
  }

  @Test
  public void coincideElValorDelPrimerDato() {
    Assertions.assertEquals(12, tipoDeConsumo0.getCantidadConsumida());
  }

  @Test
  public void coincideLaPeriodicidadDelPrimerDato() {
    Assertions.assertEquals(Periodicidad.MENSUAL, tipoDeConsumo0.getPeriodicidad());
  }

  @Test
  public void coincideElPeriodoDeImputacionDelPrimerDato() {
    Assertions.assertEquals(LocalDate.parse("12/2022"), tipoDeConsumo0.getPeriodoDeImputacion());
  }

  // SEGUNDO DATO

  @Test
  public void coincideElConsumoDelSegundoDato() {
    Assertions.assertEquals(RepositorioConsumos.tipoDeConsumo("nafta"), tipoDeConsumo1.getConsumo());
  }

  @Test
  public void coincideElValorDelSegundoDato() {
    Assertions.assertEquals(1.4, tipoDeConsumo1.getCantidadConsumida());
  }

  @Test
  public void coincideLaPeriodicidadDelSegundoDato() {
    Assertions.assertEquals(Periodicidad.ANUAL, tipoDeConsumo1.);
  }

  @Test
  public void coincideElPeriodoDeImputacionDelSegundoDato() {
    Assertions.assertEquals(LocalDate.parse("2011"), tipoDeConsumo1.getPeriodoDeImputacion());
  }*/
}