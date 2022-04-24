package validador;

import java.util.*;
import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorContrasenia {
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

    public ResultadoDeValidacion validar(String contrasenia) {
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

