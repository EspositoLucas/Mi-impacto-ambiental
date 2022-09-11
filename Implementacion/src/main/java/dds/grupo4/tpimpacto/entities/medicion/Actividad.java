package dds.grupo4.tpimpacto.entities.medicion;

import java.util.stream.Stream;

public enum Actividad {
    COMBUSTION_FIJA("Combustión fija"),
    COMBUSTION_MOVIL("Combustión móvil"),
    ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA("Electricidad adquirida y consumida"),
    LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS("Logística de productos y residuos");

    private final String nombre;

    Actividad(String nombre) {
        this.nombre = nombre;
    }

    public static Actividad from(String nombre) {
        return Stream.of(Actividad.values())
                .filter(actividad -> actividad.nombre.equals(nombre))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No se reconoce la actividad: " + nombre));
    }

    @Override
    public String toString() {
        return nombre;
    }
}
