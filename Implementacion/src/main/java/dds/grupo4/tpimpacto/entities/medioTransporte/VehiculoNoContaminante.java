package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.config.GeoApiConfig;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.GeoService;
import dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.GeoServiceImpl;
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
    public double getCombustibleConsumidoPorKm() {
        // Un vehiculo no contaminante no usa combustible, asi que gasta siempre 0
        return 0;
    }
}
