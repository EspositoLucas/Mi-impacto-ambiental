package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoCombustible;
import dds.grupo4.tpimpacto.enums.TipoVehiculoParticular;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VehiculoParticular")
@Table(name = "vehiculos_particulares")
public class VehiculoParticular extends MedioDeTransporte {

    private TipoVehiculoParticular tipoVehiculoParticular;
    private TipoCombustible tipoVCombustible;
    private String tipoServicioContratado;

    // Hibernate
    protected VehiculoParticular() {
    }

    public VehiculoParticular(TipoVehiculoParticular tipoVehiculoParticular, TipoCombustible tipoVCombustible, String tipoServicioContratado) {
        this.tipoVehiculoParticular = tipoVehiculoParticular;
        this.tipoVCombustible = tipoVCombustible;
        this.tipoServicioContratado = tipoServicioContratado;
    }

    public TipoVehiculoParticular getTipoVehiculoParticular() {
        return tipoVehiculoParticular;
    }

    public void setTipoVehiculoParticular(TipoVehiculoParticular tipoVehiculoParticular) {
        this.tipoVehiculoParticular = tipoVehiculoParticular;
    }

    public TipoCombustible getTipoVCombustible() {
        return tipoVCombustible;
    }

    public void setTipoVCombustible(TipoCombustible tipoVCombustible) {
        this.tipoVCombustible = tipoVCombustible;
    }

    public String getTipoServicioContratado() {
        return tipoServicioContratado;
    }

    public void setTipoServicioContratado(String tipoServicioContratado) {
        this.tipoServicioContratado = tipoServicioContratado;
    }

    @Override
    public double distanciaRecorrida() {
        return 0;
    }
}
