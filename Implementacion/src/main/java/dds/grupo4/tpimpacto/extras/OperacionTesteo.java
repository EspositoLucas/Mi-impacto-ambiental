package dds.grupo4.tpimpacto.extras;

import java.util.Arrays;

public enum OperacionTesteo {
    LOGIN(1),
    REGISTRO(2),
    CARGAR_MEDICIONES(3),
    CREAR_ORGANIZACION(4),
    LISTAR_ORGANIZACIONES(5),
    CREAR_MIEMBRO(6),
    ACEPTAR_SOLICITUD(7),
    LISTAR_MIEMBROS_DE_ORGANIZACION(8),
    CREAR_TIPO_SERVICIO_CONTRATADO(9),
    EXIT(10);

    private final int value;

    OperacionTesteo(int value) {
        this.value = value;
    }

    public static OperacionTesteo valueOf(int operacion) {
        return Arrays.stream(values())
                .filter(operacionTesteo -> operacionTesteo.value == operacion)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
