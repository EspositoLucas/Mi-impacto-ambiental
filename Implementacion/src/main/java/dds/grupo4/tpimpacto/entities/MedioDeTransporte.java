package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "MedioDeTransporte")
@Table(name = "medios_de_transporte")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedioDeTransporte extends BaseEntity {

    // Hibernate
    protected MedioDeTransporte() {
    }

    public abstract double distanciaRecorrida() ;


    // litros de combustible consumidos por medio de trasnporte

//    public double litrosConsumido() {
//        return 0;
//    }
}
