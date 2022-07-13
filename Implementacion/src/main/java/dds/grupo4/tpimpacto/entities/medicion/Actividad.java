package dds.grupo4.tpimpacto.entities.medicion;

public enum Actividad {
    CombustionFija,
    CombustionMovil,
    ElectricidadAdquiridaYConsumida,
    LogisticaDeProductosYResiduos;

    public static Actividad from(String actividad) {
        switch (actividad) {
            case "Combustion fija":
                return CombustionFija;
            case "Combustion movil":
                return CombustionMovil;
            case "Electricidad adquirida y consumida":
                return ElectricidadAdquiridaYConsumida;
            case "Logistica de productos y residuos":
                return LogisticaDeProductosYResiduos;
            default:
                throw new IllegalArgumentException("No se reconoce la actividad: " + actividad);
        }
    }
}
