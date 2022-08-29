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
    FactorDeEmision factorDistintaUnidad1 = new FactorDeEmision(0.7 , UnidadFactorEmision.KGCO2eq );
    TipoConsumo consumo = new TipoConsumo("Carbon", Actividad.CombustionFija,factorDistintaUnidad1,"Directo");
    FactorDeEmision factorDistintaUnidad2 = new FactorDeEmision(0.5 , UnidadFactorEmision.GCO2eq );
    consumo.setFactorDeEmision(factorDistintaUnidad2);
  }
}