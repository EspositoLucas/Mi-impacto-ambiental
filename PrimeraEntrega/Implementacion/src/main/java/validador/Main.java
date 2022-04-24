package validador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Ingrese la contraseña: ");

        Scanner scanner = new Scanner(System.in);

        String contrasenia = scanner.nextLine();

        ValidadorContrasenia validador = new ValidadorContrasenia();
        ResultadoDeValidacion resultado;
        do {
            resultado = validador.validar(contrasenia);
            if (resultado.esValido()) {
                System.out.println("Contrasenia valida!");
            } else {
                System.out.println("Contrasenia invalida:");
                System.out.println(resultado.getErroresEnLineas());
                System.out.print("\nIngrese otra contrasenia: ");
                contrasenia = scanner.nextLine();
            }
        } while (!resultado.esValido());
    }
}
