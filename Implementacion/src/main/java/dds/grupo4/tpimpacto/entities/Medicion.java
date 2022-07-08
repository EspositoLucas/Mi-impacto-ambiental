package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.Actividad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Medicion")
@Table(name = "mediciones")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Medicion extends BaseEntity {


    private Actividad actividad;
    private TipoConsumo tipoConsumo;
    private Double valorConsumo;
    // private String unidad;
    private String periodicidad;
    private String periodoAmputacion;

    public Medicion(Actividad actividad, TipoConsumo tipoConsumo, Double valorConsumo, String periodicidad, String periodoAmputacion) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
        this.periodicidad = periodicidad;
        this.periodoAmputacion = periodoAmputacion;
    }

    public double calculoHCDatoActividad() {
        return valorConsumo * this.tipoConsumo.getFactorEmision().getValor();
    }
}
