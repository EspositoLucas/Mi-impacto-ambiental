package dds.grupo4.tpimpacto.utils;

public class UnitUtils {

    public static double convertToUnit(double valor, String unidadActual, String unidadFinal) {
        switch (unidadFinal) {
            case "KM":
                switch (unidadActual) {
                    case "KM":
                        return valor;
                    case "M":
                        return valor / 1000;
                }
                break;
            case "M":
                switch (unidadActual) {
                    case "KM":
                        return valor * 1000;
                    case "M":
                        return valor;
                }
                break;
        }

        throw new IllegalArgumentException("Unidades no soportadas");
    }

}
