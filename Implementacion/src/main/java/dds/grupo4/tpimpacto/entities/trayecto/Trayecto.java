package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.utils.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private Lugar inicio;

    @ManyToOne
    @JoinColumn(
            name = "fin",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Trayectos_Fin")
    )
    private Lugar fin;

    @OneToMany(mappedBy = "trayecto")
    private List<MiembroPorTrayecto> miembrosPorTrayecto;

    @OneToMany(mappedBy = "trayecto")
    private List<Tramo> tramos = new ArrayList<>();

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Trayecto(Lugar inicio, Lugar fin, List<Tramo> tramos) {
        this.inicio = inicio;
        this.fin = fin;
        this.tramos = tramos;
    }

    public double getDistanciaTotalRecorrida() {
        return tramos.stream()
                .mapToDouble(Tramo::getDistanciaRecorrida)
                .sum();
    }

    public boolean seRealizaEnFecha(LocalDate fecha) {
        return DateTimeUtils.isAfterOrEqual(fecha, fechaInicio) && DateTimeUtils.isBeforeOrEqual(fecha, fechaFin);
    }
}
