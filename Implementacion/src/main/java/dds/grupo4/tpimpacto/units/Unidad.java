package dds.grupo4.tpimpacto.units;

import lombok.Getter;

@Getter
public enum Unidad {
    /** Metros cubicos (BASE) */
    M3(TipoUnidad.VOLUMEN, true, 1d),
    /** Litros */
    LT(TipoUnidad.VOLUMEN, false, 1d/1000),
    /** Kilogramos (BASE) */
    KG(TipoUnidad.PESO, true, 1d),
    /** Gramos */
    G(TipoUnidad.PESO, false, 1d/1000),
    /** Kilowatt-Hora (BASE) */
    KWH(TipoUnidad.ENERGIA, true, 1d),
    /** Kilometro (BASE) */
    KM(TipoUnidad.DISTANCIA, true, 1d),
    /** Metro */
    M(TipoUnidad.DISTANCIA, false, 1d/1000),
    /** Gramo equivalente carbono (BASE) */
    GCO2eq(TipoUnidad.EQUIVALENTE_CARBONO, true, 1d),
    /** Kilogramo equivalente carbono */
    KGCO2eq(TipoUnidad.EQUIVALENTE_CARBONO, false, 1000d),
    /** Tonelada equivalente carbono */
    TNCO2eq(TipoUnidad.EQUIVALENTE_CARBONO, false, 1_000_000d);

    private final TipoUnidad tipoUnidad;
    private final boolean base;
    private final double factorConversionAUnidadBase;

    Unidad(TipoUnidad tipoUnidad, boolean base, double factorConversionAUnidadBase) {
        this.tipoUnidad = tipoUnidad;
        this.base = base;
        this.factorConversionAUnidadBase = factorConversionAUnidadBase;
        if (this.base) {
            this.tipoUnidad.setUnidadBase(this);
        }
    }

    public double factorConversionDesdeUnidadBase() {
        return 1d / factorConversionAUnidadBase;
    }
}
