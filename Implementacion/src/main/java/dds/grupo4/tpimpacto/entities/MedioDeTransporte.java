package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "MedioDeTransporte")
@Table(name = "medios_de_transporte")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedioDeTransporte extends BaseEntity {

    protected final String API_TOKEN = "dbNsJcYAneNbF8+i9DX735F7KR7mPZVELmu1wN+Nx0o=";

    // Hibernate
    protected MedioDeTransporte() {
    }

    /**
     * Devuelve la distancia en KM entre el Lugar inicial y el Lugar final
     */
    public abstract double distanciaRecorrida(Lugar lugarInicio, Lugar lugarFin);


    // litros de combustible consumidos por medio de trasnporte

//    public double litrosConsumido() {
}
