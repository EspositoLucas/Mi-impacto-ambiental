package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.UnidadFactorEmision;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "FactorEmision")
@Table(name = "factores_de_emision")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FactorEmision extends BaseEntity {
    private Double valor;
    private UnidadFactorEmision unidad;

    public FactorEmision(Double valor, UnidadFactorEmision unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }



}
