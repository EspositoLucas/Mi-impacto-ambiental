package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "MedioDeTransporte")
@Table(name = "medios_de_transporte")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MedioDeTransporte extends BaseEntity {

    protected final String API_TOKEN = "dbNsJcYAneNbF8+i9DX735F7KR7mPZVELmu1wN+Nx0o=";

    private FactorEmision factorEmision;

    /**
     * Devuelve la distancia en KM entre el Lugar inicial y el Lugar final
     */
    public abstract double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin);


    // litros de combustible consumidos por medio de trasnporte

    //    public double cantConsumidaCombustible() {
}
