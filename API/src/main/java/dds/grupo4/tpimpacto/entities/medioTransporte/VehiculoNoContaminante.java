package dds.grupo4.tpimpacto.entities.medioTransporte;

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
    @Override
    public double getCombustibleConsumidoPorKm() {
        // Un vehiculo no contaminante no usa combustible, asi que gasta siempre 0
        return 0;
    }

    @Override
    public String toString() {
        return getTipoMedioDeTransporte().toString();
    }
}
