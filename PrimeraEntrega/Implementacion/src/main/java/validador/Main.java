package validador;

import java.util.Scanner;
import java.util.Timer;
<<<<<<< HEAD
import java.util.TimerTask;
=======
>>>>>>> a383a87685b598656836f9671ffe394d48f1f6dc

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
                int formula = intentosFallidos + 5;
                System.out.println(resultado.getErroresEnLineas());

                intentosFallidos++;

                Timer timer = new Timer();
                if(formula >= 5){
                    timer.schedule(new NewLine(), 0, formula * 1000);
                    contrasenia = scanner.nextLine();
                }
            }
        } while (!resultado.esValido());
    }
}
class NewLine extends TimerTask {
    public void run() {
        int formula = 5;
        System.out.println("Cuenta bloqueada por "+ formula + " segundos");
        //System.out.print("\nIngrese otra contrasenia: ");
    }
}