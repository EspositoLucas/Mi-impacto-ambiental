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
    private double combustibleConsumidoPorKm;

    @ManyToOne
    @JoinColumn(name = "tipo_servicio_contratado", nullable = false)
    private TipoServicioContratado tipoServicioContratado;

    public VehiculoParticular(TipoVehiculoParticular tipoVehiculoParticular, Combustible combustible, TipoServicioContratado tipoServicioContratado, Double combustibleConsumidoPorKm) {
        this.tipoVehiculoParticular = tipoVehiculoParticular;
        this.combustible = combustible;
        this.tipoServicioContratado = tipoServicioContratado;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

}
