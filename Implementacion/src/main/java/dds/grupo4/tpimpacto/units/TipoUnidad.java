package dds.grupo4.tpimpacto.units;

import lombok.Getter;

@Getter
public enum TipoUnidad {
    DISTANCIA,
    PESO,
    VOLUMEN,
    ENERGIA,
    EQUIVALENTE_CARBONO;

    private Unidad unidadBase;

    public void setUnidadBase(Unidad unidadBase) {
        this.unidadBase = unidadBase;
    }
}
