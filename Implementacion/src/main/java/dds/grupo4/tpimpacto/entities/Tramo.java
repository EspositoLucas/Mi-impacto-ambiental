package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Tramo")
@Table(name = "tramos")
public class Tramo extends BaseEntity {

    private Trayecto trayecto;
    private MedioDeTransporte medioDeTransporte;

    // TODO: ver si estos dos campos no eran de tipo Direccion
    private Espacio direccionInicio;
    private Espacio direccionFin;

    // Hibernate
    protected Tramo() {
    }

    public Tramo(Trayecto trayecto, MedioDeTransporte medioDeTransporte, Espacio direccionInicio, Espacio direccionFin) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
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

    public Espacio getDireccionInicio() {
        return direccionInicio;
    }

    public void setDireccionInicio(Espacio direccionInicio) {
        this.direccionInicio = direccionInicio;
    }

    public Espacio getDireccionFin() {
        return direccionFin;
    }

    public void setDireccionFin(Espacio direccionFin) {
        this.direccionFin = direccionFin;
    }
}
