package validador;

import java.util.Scanner;

public class Main {
    private static final int MAX_INTENTOS = 3;

    public static void main(String[] args) throws Exception {
        System.out.print("Ingrese la contraseña: ");

        Scanner scanner = new Scanner(System.in);

        String contrasenia = scanner.nextLine();

        ValidadorContrasenia validador = new ValidadorContrasenia();
        ResultadoDeValidacion resultado;
        int intentosFallidos = 0;
        do {
            resultado = validador.validar(contrasenia);
            if (resultado.esValido()) {
                System.out.println("Contrasenia valida!");
            } else {
                System.out.println("Contrasenia invalida:");
                System.out.println(resultado.getErroresEnLineas());

                intentosFallidos++;
                if (intentosFallidos >= MAX_INTENTOS) {
                    System.out.println("Cuenta bloqueada por haber hecho demasiados intentos fallidos");
                } else {
                    System.out.print("\nIngrese otra contrasenia: ");
                    contrasenia = scanner.nextLine();
                }
            }
        } while (!resultado.esValido() && intentosFallidos < MAX_INTENTOS);
    }
}
