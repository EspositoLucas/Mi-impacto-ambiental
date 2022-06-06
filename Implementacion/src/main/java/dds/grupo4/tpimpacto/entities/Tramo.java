package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tramo")
@Table(name = "tramos")
public class Tramo extends BaseEntity {

    private Trayecto trayecto;
    private MedioDeTransporte medioDeTransporte;
    private Lugar lugarInicio;
    private Lugar lugarFin;
    private List<Miembro> miembros = new ArrayList<>();

    // Hibernate
    protected Tramo() {
    }

    public Tramo(Trayecto trayecto, MedioDeTransporte medioDeTransporte, Lugar lugarInicio, Lugar lugarFin, List<Miembro> miembros) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
        this.miembros = miembros;
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

    public Lugar getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(Lugar lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public Lugar getLugarFin() {
        return lugarFin;
    }

    public void setLugarFin(Lugar lugarFin) {
        this.lugarFin = lugarFin;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.addTramo(this);
    }

    public double distancia() {
        return medioDeTransporte.distanciaRecorrida(lugarInicio, lugarFin);
    }

    // cant de litros consumidos por un medio de trasnporte en un tramo

//    public double litrosConsumidos() {
//        return medioDeTransporte.litrosConsumidos();
//    }

}
