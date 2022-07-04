package dds.grupo4.tpimpacto.entities;

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

    /* TODO: todos estos campos String probablemente tengan que ser una Clase aparte (por ejemplo una
     *  clase Actividad (o enum), una clase/enum TipoConsumo...), pero por ahora lo dejamos asi
     */
    private String actividad;
    private String tipoConsumo;
    private Double valorConsumo;
    // private String unidad;
    private String periodicidad;
    private String periodoAmputacion;

    private CalculoHCActividad calculoHC;

    public Medicion(String actividad, String tipoConsumo, Double valorConsumo, String periodicidad, String periodoAmputacion, CalculoHCActividad calculoHC) {
        this.actividad = actividad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
        this.periodicidad = periodicidad;
        this.periodoAmputacion = periodoAmputacion;
        this.calculoHC = calculoHC;
    }
}
