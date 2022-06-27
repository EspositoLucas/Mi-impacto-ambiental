package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Medicion")
@Table(name = "mediciones")
public class Medicion extends BaseEntity {

    /* TODO: todos estos campos String probablemente tengan que ser una Clase aparte (por ejemplo una
     *  clase Actividad (o enum), una clase/enum TipoConsumo...), pero por ahora lo dejamos asi
     */
    private String actividad;
    private String tipoConsumo;
    private Double valorConsumo;
    // private String unidad;
    private String periodicidad;
    private String periodoAmputacion;

    private CalculoHCActividad calculoHC ;

    // Hibernate
    protected Medicion() {
    }

    public Medicion(String actividad, String tipoConsumo, Double valorConsumo, String periodicidad, String periodoAmputacion) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
        this.periodicidad = periodicidad;
        this.periodoAmputacion = periodoAmputacion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public Double getValorConsumo() {
        return valorConsumo;
    }

    public void setValorConsumo(Double valorConsumo) {
        this.valorConsumo = valorConsumo;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getPeriodoAmputacion() {
        return periodoAmputacion;
    }

    public void setPeriodoAmputacion(String periodoAmputacion) {
        this.periodoAmputacion = periodoAmputacion;
    }


}
