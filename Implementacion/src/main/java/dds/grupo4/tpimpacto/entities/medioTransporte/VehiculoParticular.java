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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "VehiculoParticular")
@Table(name = "vehiculos_particulares")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VehiculoParticular extends MedioDeTransporte {

    private TipoVehiculoParticular tipoVehiculoParticular;
    private Combustible combustible;

    @ManyToOne
    @JoinColumn(name = "???tipo_servicio_contratado???", nullable = false)
    private TipoServicioContratado tipoServicioContratado;

    private Double cantConmbustibleXKm;

    public VehiculoParticular(TipoVehiculoParticular tipoVehiculoParticular, Combustible combustible, TipoServicioContratado tipoServicioContratado, Double cantConmbustibleXKm) {
        this.tipoVehiculoParticular = tipoVehiculoParticular;
        this.combustible = combustible;
        this.tipoServicioContratado = tipoServicioContratado;
        this.cantConmbustibleXKm = cantConmbustibleXKm;
    }

    @Override
    public double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin) {
        // TODO: pensar alguna forma mas testeable de implementar esto
        GeoService geoService = new GeoServiceImpl(new GeoApiConfig(API_TOKEN));
        return geoService.distanciaRecorrida(lugarInicio, lugarFin);
    }

    @Override
    public double cantConsumidaCombustible(Lugar lugarInicio, Lugar lugarFin) {
        return cantConmbustibleXKm * this.distanciaRecorrida(lugarInicio, lugarFin);
    }

}
