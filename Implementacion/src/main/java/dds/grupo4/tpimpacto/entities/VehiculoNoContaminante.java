package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoVehiculoNoContaminante;

public class VehiculoNoContaminante extends MedioTransporte {
    private TipoVehiculoNoContaminante tipoVehiculoNoContaminante;

    public VehiculoNoContaminante(TipoVehiculoNoContaminante tipoVehiculoNoContaminante) {
        this.tipoVehiculoNoContaminante = tipoVehiculoNoContaminante;
    }

    public TipoVehiculoNoContaminante getTipoVehiculoNoContaminante() {
        return tipoVehiculoNoContaminante;
    }

    public void setTipoVehiculoNoContaminante(TipoVehiculoNoContaminante tipoVehiculoNoContaminante) {
        this.tipoVehiculoNoContaminante = tipoVehiculoNoContaminante;
    }

}
