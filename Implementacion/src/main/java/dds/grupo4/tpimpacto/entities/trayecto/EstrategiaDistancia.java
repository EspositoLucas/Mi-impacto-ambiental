package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.cargamediciones.MedicionesDataLoader;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerators;

@Getter
@Setter
public  abstract class EstrategiaDistancia {

    private MedioDeTransporte medioDeTransporte ;

    public abstract double calcularDistanciaRecorrida(Lugar lugarInicial, Lugar lugarFinal) ;

}
