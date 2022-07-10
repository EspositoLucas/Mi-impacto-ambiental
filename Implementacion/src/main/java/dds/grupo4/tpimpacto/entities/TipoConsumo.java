package dds.grupo4.tpimpacto.entities;

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

    private String nombre ;
    private FactorEmision factorEmision ;


    public TipoConsumo(String nombre, FactorEmision factorEmision) {
        this.nombre = nombre;
        this.factorEmision = factorEmision;
    }
}
