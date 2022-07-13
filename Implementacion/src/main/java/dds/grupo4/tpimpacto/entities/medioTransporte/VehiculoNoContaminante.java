package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.entities.Lugar;
import dds.grupo4.tpimpacto.services.apiSwagger.GeoService;
import dds.grupo4.tpimpacto.services.apiSwagger.GeoServiceImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "VehiculoNoContaminante")
@Table(name = "vehiculos_no_contaminantes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VehiculoNoContaminante extends MedioDeTransporte {

    private TipoVehiculoNoContaminante tipoVehiculoNoContaminante;

    public VehiculoNoContaminante(TipoVehiculoNoContaminante tipoVehiculoNoContaminante) {
        this.tipoVehiculoNoContaminante = tipoVehiculoNoContaminante;
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        // TODO: pensar alguna forma mas testeable de implementar esto
        GeoService geoService = new GeoServiceImpl(new GeoApiConfig(API_TOKEN));
        return geoService.distanciaRecorrida(lugarInicio, lugarFin);
    }

    @Override
    public double cantConsumidaCombustible(Lugar lugarInicio, Lugar lugarFin) {
            return 0;
   }

}
