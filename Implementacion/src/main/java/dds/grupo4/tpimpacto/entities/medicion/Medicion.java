package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static dds.grupo4.tpimpacto.entities.medicion.Actividad.LogisticaDeProductosYResiduos;

@Entity(name = "Medicion")
@Table(name = "mediciones")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicion extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false)
    private Organizacion organizacion;

    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "???tipo_consumo???", nullable = false)
    private TipoConsumo tipoConsumo;
    private Double valorConsumo;
    private String periodicidad;
    private String periodoAmputacion;

    private MedioDeTransporte medioDeTransporte ;



    public Medicion(Actividad actividad, TipoConsumo tipoConsumo, Double valorConsumo, String periodicidad, String periodoAmputacion) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
        this.periodicidad = periodicidad;
        this.periodoAmputacion = periodoAmputacion;
    }

    public double calculoHCDatoActividad() {
        if(getActividad() == LogisticaDeProductosYResiduos){
            return this.organizacion.getFactorK() * this.tipoConsumo.getPeso() * this.tipoConsumo.getDistanciaMediaRecorrida() * this.medioDeTransporte.getFactorEmision().getValor() ;
        }
        else return valorConsumo * this.tipoConsumo.getFactorEmision().getValor();

    }
}
