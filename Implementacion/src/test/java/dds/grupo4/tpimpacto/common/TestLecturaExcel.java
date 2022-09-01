package dds.grupo4.tpimpacto.common;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.services.OrganizacionServiceTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;



@Disabled
public class TestLecturaExcel extends OrganizacionServiceTests {

    private Medicion medicion0;
    private Medicion medicion1;


    @BeforeEach
    void inicializarDatosDeActividades() {

    setUp();
//    this.organizacionService.cargarMediciones();
//
//    medicion0 = organizacion.getMediciones().get(0);
//    medicion1 = organizacion.getMediciones().get(1);

    }
    // así lee el excel:
    // [ ["gas_natural" "12" "mensual" "12/2022"] ["nafta" "1.4" "anual" "2011"] [

    // PRIMER DATO MENSUAL
/*
  @Test
  public void coincideElConsumoDelPrimerDato() {
    Assertions.assertEquals("Gas natural", medicion0.getTipoDeConsumo().getNombre());
  }

  @Test
  public void coincideElValorDelPrimerDato() {
    Assertions.assertEquals("12",  medicion0.getValor());
  }

  @Test
  public void coincideLaPeriodicidadDelPrimerDato() {
    Assertions.assertEquals(Periodicidad.MENSUAL,  medicion0.getPeriodicidad);
  }

  @Test
  public void coincideElPeriodoDeImputacionDelPrimerDato() {
    Assertions.assertEquals(LocalDate.parse("12/2022", medicicion.getPeriodoDeImputacion());
  }

  // SEGUNDO DATO ANUAL

  @Test
  public void coincideElConsumoDelSegundoDato() {
    Assertions.assertEquals("Nafta", medicion1.getTipoDeConsumo().getNombre());
  }

  @Test
  public void coincideElValorDelSegundoDato() {
    Assertions.assertEquals("1.4", medicion1.getValor());
  }

  @Test
  public void coincideLaPeriodicidadDelSegundoDato() {
    Assertions.assertEquals(Periodicidad.ANUAL, medicion1.getPeriodicidad());
  }

  @Test
  public void coincideElPeriodoDeImputacionDelSegundoDato() {
    Assertions.assertEquals(LocalDate.parse("2011", medicicion1.getPeriodoDeImputacion());
  }*/
}