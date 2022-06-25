package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.enums.TipoVehiculoNoContaminante;
import dds.grupo4.tpimpacto.services.apiSwagger.GeoService;
import dds.grupo4.tpimpacto.services.apiSwagger.GeoServiceImpl;

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
        // TODO: pensar alguna forma mas testeable de implementar esto
        GeoService geoService = new GeoServiceImpl(new GeoApiConfig(API_TOKEN));
        return geoService.distanciaRecorrida(lugarInicio, lugarFin);
    }

//    @Override
//    public double cantConsumidaCombustible() {
//   return 0;
//   }

}
