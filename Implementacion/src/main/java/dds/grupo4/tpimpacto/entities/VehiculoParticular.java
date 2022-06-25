package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.enums.TipoVehiculoParticular;
import dds.grupo4.tpimpacto.services.apiSwagger.GeoService;
import dds.grupo4.tpimpacto.services.apiSwagger.GeoServiceImpl;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VehiculoParticular")
@Table(name = "vehiculos_particulares")
public class VehiculoParticular extends MedioDeTransporte {

    private TipoVehiculoParticular tipoVehiculoParticular;
    private Combustible combustible;
    private TipoServicioContratado tipoServicioContratado;

    // Hibernate
    protected VehiculoParticular() {
    }

    public VehiculoParticular(TipoVehiculoParticular tipoVehiculoParticular, Combustible combustible, TipoServicioContratado tipoServicioContratado) {
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

    public TipoServicioContratado getTipoServicioContratado() {
        return tipoServicioContratado;
    }

    public void setTipoServicioContratado(TipoServicioContratado tipoServicioContratado) {
        this.tipoServicioContratado = tipoServicioContratado;
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        // TODO: pensar alguna forma mas testeable de implementar esto
        GeoService geoService = new GeoServiceImpl(new GeoApiConfig(API_TOKEN));
        return geoService.distanciaRecorrida(lugarInicio, lugarFin);
    }

    //    @Override
//    public double cantConsumidaCombustible() {
//   return 0;
//   }

}
