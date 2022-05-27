package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoVehiculoNoContaminante;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VehiculoNoContaminante")
@Table(name = "vehiculos_no_contaminantes")
public class VehiculoNoContaminante extends MedioDeTransporte {

    private TipoVehiculoNoContaminante tipoVehiculoNoContaminante;

    // Hibernate
    protected VehiculoNoContaminante() {
    }

    public VehiculoNoContaminante(TipoVehiculoNoContaminante tipoVehiculoNoContaminante) {
        this.tipoVehiculoNoContaminante = tipoVehiculoNoContaminante;
    }

    public TipoVehiculoNoContaminante getTipoVehiculoNoContaminante() {
        return tipoVehiculoNoContaminante;
    }

    public void setTipoVehiculoNoContaminante(TipoVehiculoNoContaminante tipoVehiculoNoContaminante) {
        this.tipoVehiculoNoContaminante = tipoVehiculoNoContaminante;
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        // TODO: se llama al Servicio Externo
        return 0;
    }

//    @Oveeride
//    //    public double litrosConsumido() {
////        return 0;
////    }

}
