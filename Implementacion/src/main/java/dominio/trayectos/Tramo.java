package dominio.trayectos;

import dominio.Espacio;
import dominio.transportes.MedioTransporte;

public class Tramo {

    private Trayecto trayecto ;
    private MedioTransporte mediotransporte;
    private Espacio direccionInicio ;
    private Espacio direccionFin ;


    public Tramo(Trayecto trayecto, MedioTransporte mediotransporte, Espacio direccionInicio, Espacio direccionFin) {
        this.trayecto = trayecto;
        this.mediotransporte = mediotransporte;
        this.direccionInicio = direccionInicio;
        this.direccionFin = direccionFin;
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public MedioTransporte getMediotransporte() {
        return mediotransporte;
    }

    public void setMediotransporte(MedioTransporte mediotransporte) {
        this.mediotransporte = mediotransporte;
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
