package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.medioTransporte.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCrearTransporteParticular {

  @Test
  public void altaVehiculoParticular() {
    Combustible combustible = new Combustible(TipoCombustible.GASOIL, UnidadCombustible.L);
    TipoServicioContratado servicio = new TipoServicioContratado("Taxi");
    VehiculoParticular vehiculoParticular = new VehiculoParticular(TipoVehiculoParticular.AUTO,combustible,servicio,0.09);
    Assertions.assertEquals(vehiculoParticular.getTipoVehiculoParticular(), TipoVehiculoParticular.AUTO);
  }
}