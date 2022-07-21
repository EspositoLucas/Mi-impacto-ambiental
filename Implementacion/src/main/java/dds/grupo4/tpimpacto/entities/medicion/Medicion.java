package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private Double valorConsumo;
    private String periodicidad;
    private String periodoAmputacion;

    @ManyToOne
    @JoinColumn(name = "medio_de_transporte", nullable = false,
            foreignKey = @ForeignKey(name = "FK_Mediciones_MedioDeTransporte"))
    private MedioDeTransporte medioDeTransporte;

    public Medicion(Actividad actividad, TipoConsumo tipoConsumo, Double valorConsumo, String periodicidad, String periodoAmputacion) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
        this.periodicidad = periodicidad;
        this.periodoAmputacion = periodoAmputacion;
    }

    public double calculoHCDatoActividad() {
        if (getActividad() == Actividad.LogisticaDeProductosYResiduos) {
            return this.organizacion.getFactorK() * this.tipoConsumo.getPeso() * this.tipoConsumo.getDistanciaMediaRecorrida() * this.medioDeTransporte.getFactorEmision().getValor();
        }
        return valorConsumo * this.tipoConsumo.getFactorEmision().getValor();
    }
}
