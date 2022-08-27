package entities.medioTrasnporte;

import dds.grupo4.tpimpacto.entities.medioTransporte.*;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestCrearTransportePublico {

    @Test
    public void altaTransportePublico() {
        Parada liniers = new Parada(new Direccion("Av General Paz", "10880", "Argentina","Buenos Aires","","CABA","Liniers",1111), 5.5);
        List<Parada> estaciones = Arrays.asList(liniers);
        String linea = "Sarmiento";
        Combustible combustible = new Combustible(TipoCombustible.ELECTRICO,UnidadCombustible.L);
        TransportePublico transportePublico = new TransportePublico(TipoTransportePublico.TREN, linea,estaciones,combustible,0.7);
        Assertions.assertEquals(transportePublico.getTipoTransportePublico(), TipoTransportePublico.TREN);
        Assertions.assertEquals(transportePublico.getLinea(), linea);
    }

}
