package dds.grupo4.tpimpacto.entities.medicion;

public enum Periodicidad {
    MENSUAL,
    ANUAL;

    public static Periodicidad from(String periodicidad) {
        switch (periodicidad) {
            case "Anual":
                return ANUAL;
            case "Mensual":
                return MENSUAL;
            default:
                throw new IllegalArgumentException("No se reconoce la periodicidad: " + periodicidad);
        }
    }
}
