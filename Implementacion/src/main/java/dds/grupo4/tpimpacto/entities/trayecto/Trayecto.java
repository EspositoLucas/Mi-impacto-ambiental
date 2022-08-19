package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity(name = "Trayecto")
@Table(name = "trayectos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trayecto extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "inicio",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Trayectos_Inicio")
    )
    private Direccion inicio;

    @ManyToOne
    @JoinColumn(
            name = "fin",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Trayectos_Fin")
    )
    private Direccion fin;

    @OneToMany(mappedBy = "trayecto")
    private List<Tramo> tramos = new ArrayList<>();


    private LocalDate mesDeInicio; // periodo de inicio - esto coincide con la fecha de ingreso del miembro pero para el primer trayecto

    private LocalDate mesDeFin; // periodo de fin


    public Trayecto(Direccion inicio, Direccion fin, List<Tramo> tramos) {
        this.inicio = inicio;
        this.fin = fin;
        this.tramos = tramos;
    }

    public double calcularDistanciaTotal() {
        return tramos.stream()
                .mapToDouble(Tramo::getDistanciaRecorrida)
                .sum();
    }
    public boolean seRealizaEntre(LocalDate fecha) {

        return mesDeInicio.compareTo(fecha) <0 && mesDeFin.compareTo(fecha) >=0;
    }
}
