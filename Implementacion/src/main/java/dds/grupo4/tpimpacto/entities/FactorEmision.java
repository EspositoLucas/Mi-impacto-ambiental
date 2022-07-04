package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.UnidadFactorEmision;

public class FactorEmision {
    private Double valor;
    private UnidadFactorEmision unidad;

    public FactorEmision(Double valor, UnidadFactorEmision unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }

}
