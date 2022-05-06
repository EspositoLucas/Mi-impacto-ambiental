package dds.grupo4.tpimpacto.common;

import java.util.*;

public class ValidadorContrasenia {

    public static final String RUTA_ARCHIVO_CONTRASENIAS_INSEGURAS = "static/contraseniasInseguras.txt";

    private static final int MIN_CARACTERES = 8;
    private static final int MIN_MINUSCULAS = 2;
    private static final int MIN_MAYUSCULAS = 2;
    private static final int MIN_DIGITOS = 2;
    private static final int MIN_ESPECIALES = 2;

    private static final Set<Character> CARACTERES_ESPECIALES = new HashSet<>(Arrays.asList(
            '!',
            '"',
            '#',
            '$',
            '%',
            '&',
            '\'',
            '(',
            ')',
            '*',
            '+',
            ',',
            '-',
            '.',
            '/',
            ':',
            ';',
            '<',
            '=',
            '>',
            '?',
            '@',
            '[',
            '\\',
            ']',
            '^',
            '_',
            '`',
            '{',
            '|',
            '}',
            '~'
    ));

    private final List<String> contraseniasInseguras;

    public ValidadorContrasenia(LectorDeArchivo lectorDeArchivo) {
        this.contraseniasInseguras = lectorDeArchivo.leerLineas(RUTA_ARCHIVO_CONTRASENIAS_INSEGURAS);
    }

    public ResultadoDeValidacion validarContrasenia(String contrasenia) {
        if (contraseniasInseguras.contains(contrasenia))
            return new ResultadoDeValidacion(false, "Su contrasenia se encuentra dentro de la lista del Top 10.000 peores contraseñas");

        int uppercaseCounter = 0, lowercaseCounter = 0, digitCounter = 0, specialCounter = 0;

        int lengthContra = contrasenia.length();
        for (int i = 0; i < lengthContra; i++) {
            char c = contrasenia.charAt(i);

            if (Character.isUpperCase(c))
                uppercaseCounter++;
            else if (Character.isLowerCase(c))
                lowercaseCounter++;
            else if (Character.isDigit(c))
                digitCounter++;

            if (CARACTERES_ESPECIALES.contains(c))
                specialCounter++;
        }

        // Parametros de Validacion
        List<String> errores = new ArrayList<>();
        if (contrasenia.length() < MIN_CARACTERES)
            errores.add("Su contrasenia debe tener al menos " + MIN_CARACTERES + " caracteres");
        if (lowercaseCounter < MIN_MINUSCULAS)
            errores.add("Su contrasenia debe tener al menos " + MIN_MINUSCULAS + " letras minusculas");
        if (uppercaseCounter < MIN_MAYUSCULAS)
            errores.add("Su contrasenia debe tener al menos " + MIN_MAYUSCULAS + " letras mayusculas");
        if (digitCounter < MIN_DIGITOS)
            errores.add("Su contrasenia debe tener al menos " + MIN_DIGITOS + " digitos");
        if (specialCounter < MIN_ESPECIALES)
            errores.add("Su contrasenia debe tener al menos " + MIN_ESPECIALES + " caracteres especiales");

        boolean contraseniaValida = errores.isEmpty();
        return new ResultadoDeValidacion(contraseniaValida, errores);
    }
}
