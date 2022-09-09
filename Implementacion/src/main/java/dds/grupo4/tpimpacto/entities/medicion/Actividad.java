package dds.grupo4.tpimpacto.entities.medicion;

public enum Actividad {
    COMBUSTION_FIJA,
    COMBUSTION_MOVIL,
    ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA,
    LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS;

    public static Actividad from(String actividad) {
        switch (actividad) {
            case "Combustión fija":
                return COMBUSTION_FIJA;
            case "Combustión móvil":
                return COMBUSTION_MOVIL;
            case "Electricidad adquirida y consumida":
                return ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA;
            case "Logística de productos y residuos":
                return LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS;
            default:
                throw new IllegalArgumentException("No se reconoce la actividad: " + actividad);
        }
    }
}
