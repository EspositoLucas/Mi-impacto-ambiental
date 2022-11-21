package dds.grupo4.tpimpacto.entities.organizacion;

public enum TipoOrganizacion {
    GUBERNAMENTAL,
    ONG,
    EMPRESA,
    INSTITUCION;

    private static final TipoOrganizacion[] values = values();

    public static TipoOrganizacion getFromOrdinal(long ordinal) {
        return values[(int)ordinal];
    }
}
