package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Tramo")
@Table(name = "tramos")
public class Tramo extends BaseEntity {

    private Trayecto trayecto;
    private MedioDeTransporte medioDeTransporte;

    // TODO: ver si estos dos campos no eran de tipo Direccion
    private Espacio espacioInicio;
    private Espacio espacioFin;


    // Hibernate
    protected Tramo() {
    }

    public Tramo(Trayecto trayecto, MedioDeTransporte medioDeTransporte, Espacio espacioInicio, Espacio espacioFin) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.espacioInicio = espacioInicio;
        this.espacioFin = espacioFin;
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public MedioDeTransporte getMedioDeTransporte() {
        return medioDeTransporte;
    }

    public void setMedioDeTransporte(MedioDeTransporte medioDeTransporte) {
        this.medioDeTransporte = medioDeTransporte;
    }

    public Espacio getEspacioInicio() {
        return espacioInicio;
    }

    public void setEspacioInicio(Espacio direccionInicio) {
        this.espacioInicio = direccionInicio;
    }

    public Espacio getEspacioFin() {
        return espacioFin;
    }

    public void setEspacioFin(Espacio direccionFin) {
        this.espacioFin = direccionFin;
    }

    //Metodo para calcular la distancia de un tramo
    public double distancia() {

        return medioDeTransporte.distanciaRecorrida();
    }


    // cant de litrps consumidos por un medio de trasnporte en un tramo
//    public double litrosConsumidos() {
//        return medioDeTransporte.litrosConsumidos();
//    }
}
