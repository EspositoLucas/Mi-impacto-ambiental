package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Medicion")
@Table(name = "mediciones")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicion extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Mediciones_Organizacion"))
    private Organizacion organizacion;

    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "tipo_consumo", nullable = false, foreignKey = @ForeignKey(name = "FK_Mediciones_TipoConsumo"))
    private TipoConsumo tipoConsumo;

    private String valor;
    private Periodicidad periodicidad;

    /**
     * Si la periodicidad es MENSUAL, entonces el periodoImputacion se guarda como 01/MM/YYYY (importan
     * el Mes y el Anio).<br>
     * Si la periodicidad es ANUAL, entonces el periodoImputacion se guarda como 01/01/YYYY (solo importa el Anio).
     */
    private LocalDate periodoImputacion;

    /*
    TODO: para que seria esto?
    @ManyToOne
    @JoinColumn(name = "medio_de_transporte", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Mediciones_MedioDeTransporte"))
    private MedioDeTransporte medioDeTransporte;
     */

    public Medicion(Organizacion organizacion, Actividad actividad, TipoConsumo tipoConsumo, String valor,
                    Periodicidad periodicidad, Integer mesImputacion, Integer anioImputacion) {
        this.organizacion = organizacion;
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valor = valor;
        this.periodicidad = periodicidad;

        if (this.periodicidad == Periodicidad.MENSUAL) {
            this.periodoImputacion = LocalDate.of(anioImputacion, mesImputacion, 1);
        } else {
            this.periodoImputacion = LocalDate.of(anioImputacion, 1, 1);
        }
    }

}
