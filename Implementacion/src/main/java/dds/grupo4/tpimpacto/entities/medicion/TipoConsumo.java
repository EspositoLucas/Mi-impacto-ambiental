package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "TipoConsumo")
@Table(name = "tipos_de_consumo")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoConsumo extends BaseEntity {
    private String nombre;
    private Actividad actividad;
    private UnidadFactorEmision unidadFactorEmision;
    private String alcance;

    public TipoConsumo(String nombre, Actividad actividad, UnidadFactorEmision unidadFactorEmision, String alcance) {
        this.nombre = nombre;
        this.actividad = actividad;
        this.unidadFactorEmision = unidadFactorEmision;
        this.alcance = alcance;
    }

}
