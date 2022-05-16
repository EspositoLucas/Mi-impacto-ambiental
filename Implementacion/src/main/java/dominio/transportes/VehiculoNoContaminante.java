package dominio.transportes;

public class VehiculoNoContaminante extends MedioTransporte {
    private TipoVehiuloContaminante tipovehiculoContaminante;

    public VehiculoNoContaminante(TipoVehiuloContaminante tipovehiculoContaminante) {
        this.tipovehiculoContaminante = tipovehiculoContaminante;
    }

    public TipoVehiuloContaminante getTipovehiculoContaminante() {
        return tipovehiculoContaminante;
    }

    public void setTipovehiculoContaminante(TipoVehiuloContaminante tipovehiculoContaminante) {
        this.tipovehiculoContaminante = tipovehiculoContaminante;
    }

}
