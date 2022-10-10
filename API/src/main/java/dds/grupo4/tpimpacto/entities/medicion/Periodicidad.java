package dds.grupo4.tpimpacto.entities.medicion;

public enum Periodicidad {
    MENSUAL,
    ANUAL;

    public static Periodicidad from(String periodicidad) {
        switch (periodicidad.toUpperCase()) {
            case "ANUAL":
                return ANUAL;
            case "MENSUAL":
                return MENSUAL;
            default:
                throw new IllegalArgumentException("No se reconoce la periodicidad: " + periodicidad);
        }
    }
}
