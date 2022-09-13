package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import dds.grupo4.tpimpacto.utils.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Medicion")
@Table(name = "mediciones")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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

    private Periodicidad periodicidad;

    /**
     * Si la periodicidad es MENSUAL, entonces el periodoImputacion se guarda como 01/MM/YYYY (importan
     * el Mes y el Anio).<br>
     * Si la periodicidad es ANUAL, entonces el periodoImputacion se guarda como 01/01/YYYY (solo importa el Anio).
     */
    private LocalDate periodoImputacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor_numerico", foreignKey = @ForeignKey(name = "FK_Mediciones_ValorNumerico"))
    private Cantidad valorNumerico;

    private String valorString;

    // TODO: ver si las mediciones de Logistica tienen un MedioDeTransporte o un TipoDeMedioDeTransporte
    @ManyToOne
    @JoinColumn(name = "medio_de_transporte",
            foreignKey = @ForeignKey(name = "FK_Mediciones_MedioDeTransporte"))
    private MedioDeTransporte medioDeTransporte;

    public Medicion(Actividad actividad, TipoConsumo tipoConsumo, Periodicidad periodicidad,
                    String periodoImputacion, String valor) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.periodicidad = periodicidad;
        this.periodoImputacion = DateTimeUtils.getDateFromString(periodoImputacion);

        if (tipoConsumo.tieneValorNumerico()) {
            Unidad unidad = tipoConsumo.getUnidad();
            this.valorNumerico = new Cantidad(unidad, Double.parseDouble(valor));
        } else {
            this.valorString = valor;
        }
    }

}
