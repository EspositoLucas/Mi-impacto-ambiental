package dds.grupo4.tpimpacto.entities.medioTransporte;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "VehiculoParticular")
@Table(name = "vehiculos_particulares")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VehiculoParticular extends MedioDeTransporte {

    @ManyToOne
    @JoinColumn(
            name = "combustible",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_VehiculosParticulares_Combustible")
    )
    private Combustible combustible;

    private double combustibleConsumidoPorKm;

    @ManyToOne
    @JoinColumn(
            name = "tipo_servicio_contratado",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_VehiculosParticulares_TipoServicioContratado")
    )
    private TipoServicioContratado tipoServicioContratado;

    public VehiculoParticular(Combustible combustible, TipoServicioContratado tipoServicioContratado, Double combustibleConsumidoPorKm) {
        this.combustible = combustible;
        this.tipoServicioContratado = tipoServicioContratado;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

    @Override
    public String toString() {
        return getTipoMedioDeTransporte().toString();
    }
}
