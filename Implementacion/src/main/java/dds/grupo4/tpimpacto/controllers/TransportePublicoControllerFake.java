package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.entities.medioTransporte.Combustible;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import dds.grupo4.tpimpacto.entities.medioTransporte.TipoTransportePublico;
import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.utils.ListUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransportePublicoControllerFake {

    public void crearLinea() {
        ConsoleHelper.print("Tipo de transporte publico (tren, subte o colectivo)");
        int ingresado = ConsoleHelper.readInt();
        TipoTransportePublico tipoTransportePublico = TipoTransportePublico.COLECTIVO; // Default para facilitar los tests

        ConsoleHelper.print("Numero de linea: ");
        String linea = ConsoleHelper.readString();

        List<Parada> paradas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Direccion direccion = new Direccion("Medrano", "700", "Argentina", "Buenos Aires",
                    "???", "???", "???", 1313);
            Parada parada = new Parada(direccion, 1313.0);

            // Agarro la ultima Parada creada y le seteo la Parada siguiente a la nueva Parada
            if (!paradas.isEmpty()) {
                Parada ultimaParada = ListUtils.getLast(paradas);
                ultimaParada.setParadaSiguiente(parada);
            }

            paradas.add(parada);
        }

        Combustible combustible = null;
        Double cantConmbustibleXKm = null;
        TransportePublico transportePublico = new TransportePublico(tipoTransportePublico, linea, paradas, combustible, cantConmbustibleXKm);

        // TODO: ver como guardamos este TransportePublico (podemos crear un Service para todos los MediosDeTransporte o
        //  uno para cada tipo)
    }

}
