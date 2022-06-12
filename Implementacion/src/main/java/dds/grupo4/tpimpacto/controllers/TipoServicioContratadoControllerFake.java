package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.entities.TipoServicioContratado;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.services.TipoServicioContratadoService;
import org.springframework.stereotype.Component;

@Component
public class TipoServicioContratadoControllerFake {

    private final TipoServicioContratadoService tipoServicioContratadoService;


    public TipoServicioContratadoControllerFake(TipoServicioContratadoService tipoServicioContratadoService) {
        this.tipoServicioContratadoService = tipoServicioContratadoService;
    }

    public void crearTipoServicioContratado() {
        ConsoleHelper.print("Nombre del servicio contratado: ");
        String nombre = ConsoleHelper.readString();

        TipoServicioContratado tipoServicioContratado = new TipoServicioContratado(nombre);
        tipoServicioContratadoService.save(tipoServicioContratado);
    }
}
