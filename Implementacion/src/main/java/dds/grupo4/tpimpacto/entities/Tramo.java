package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tramo")
@Table(name = "tramos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tramo extends BaseEntity {

    private Trayecto trayecto;
    private MedioDeTransporte medioDeTransporte;
    private Lugar lugarInicio;
    private Lugar lugarFin;
    private List<Miembro> miembros = new ArrayList<>();

    public Tramo(Trayecto trayecto, MedioDeTransporte medioDeTransporte, Lugar lugarInicio, Lugar lugarFin, List<Miembro> miembros) {
        this.trayecto = trayecto;
        this.medioDeTransporte = medioDeTransporte;
        this.lugarInicio = lugarInicio;
        this.lugarFin = lugarFin;
        this.miembros = miembros;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.addTramo(this);
    }

    public double distancia() {
        return medioDeTransporte.distanciaRecorrida(lugarInicio, lugarFin);
    }

    // calculo para HC

//    public double calculoHC() {
//    return ;
//    }

}
