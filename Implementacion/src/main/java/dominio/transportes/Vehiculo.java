package dominio.transportes;

public class Vehiculo extends MedioTransporte {

    private TipoVehiculoParticular tipoVehiculoParticular;
    private TipoCombustible tipoVCombustible;
    private String tipoServicioContratado;

    public Vehiculo(TipoVehiculoParticular tipoVehiculoParticular, TipoCombustible tipoVCombustible, String tipoServicioContratado) {
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
}
