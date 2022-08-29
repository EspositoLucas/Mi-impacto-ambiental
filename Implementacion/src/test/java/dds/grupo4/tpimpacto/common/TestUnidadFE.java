package dds.grupo4.tpimpacto.common;

import dds.grupo4.tpimpacto.entities.medicion.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUnidadFE {

  @Test
  void siLasUnidadesNoCoincidenUnaExcepcionEsLanzada() {
    UnidadesIncorrectasException excepcion = Assertions.assertThrows(
        UnidadesIncorrectasException.class,
        this::crearFEConDistintasUnidades, "La unidad ingresada no concuerda con la esperada.");
  }

  void crearFEConDistintasUnidades() {
    TipoConsumo consumo = new TipoConsumo("Carbon", Actividad.CombustionFija,UnidadFactorEmision.GCO2eq,"Directo");
    FactorDeEmision factorDistintaUnidad = new FactorDeEmision(0.7 , UnidadFactorEmision.KGCO2eq );
    consumo.setFactorDeEmision(factorDistintaUnidad);
  }
}