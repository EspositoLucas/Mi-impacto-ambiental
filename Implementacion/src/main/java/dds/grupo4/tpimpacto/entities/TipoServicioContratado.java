package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "TipoServicioContratado")
@Table(name = "tipos_servicio_contratado")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoServicioContratado extends BaseEntity {

    private String nombre;

    public TipoServicioContratado(String nombre) {
        this.nombre = nombre;
    }

}
