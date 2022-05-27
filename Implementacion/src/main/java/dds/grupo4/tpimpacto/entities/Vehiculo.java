package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoCombustible;
import dds.grupo4.tpimpacto.enums.TipoVehiculoParticular;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VehiculoParticular")
@Table(name = "vehiculos_particulares")
public class Vehiculo extends MedioDeTransporte {

    private TipoVehiculoParticular tipoVehiculoParticular;

    private Combustible combustible;
    private String tipoServicioContratado;

    // Hibernate
    protected Vehiculo() {
    }

    public Vehiculo(TipoVehiculoParticular tipoVehiculoParticular, Combustible combustible, String tipoServicioContratado) {
        this.tipoVehiculoParticular = tipoVehiculoParticular;
        this.combustible = combustible;
        this.tipoServicioContratado = tipoServicioContratado;
    }

    public TipoVehiculoParticular getTipoVehiculoParticular() {
        return tipoVehiculoParticular;
    }

    public void setTipoVehiculoParticular(TipoVehiculoParticular tipoVehiculoParticular) {
        this.tipoVehiculoParticular = tipoVehiculoParticular;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public String getTipoServicioContratado() {
        return tipoServicioContratado;
    }

    public void setTipoServicioContratado(String tipoServicioContratado) {
        this.tipoServicioContratado = tipoServicioContratado;
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        // TODO: se llama al Servicio Externo
        return 0;
    }
}
