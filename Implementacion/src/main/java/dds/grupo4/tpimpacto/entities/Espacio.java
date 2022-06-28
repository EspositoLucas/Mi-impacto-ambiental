package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoEspacio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Espacio")
@Table(name = "espacios")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Espacio extends Lugar {

    private String nombre;
    private TipoEspacio tipoEspacio;

    public Espacio(Direccion direccion, String nombre, TipoEspacio tipoEspacio) {
        super(direccion);
        this.nombre = nombre;
        this.tipoEspacio = tipoEspacio;
    }

}
