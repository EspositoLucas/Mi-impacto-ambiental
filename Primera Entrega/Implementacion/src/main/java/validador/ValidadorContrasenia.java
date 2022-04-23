package validador;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher ;
import java.util.regex.Pattern;

public class ValidadorContrasenia {
    public static void main(String []args){

        // Cantidad maxima de letras permitidas
        final int MAX=8;


        // Cantidad minima de mayusculas
        final int MIN_Uppercase=2;
        // Cantidad minima de minusculas
        final int MIN_Lowercase=2;
        // Cantidad de digitos
        final int NUM_Digits=2;
        // cantidad minima de caracteres especiales
        final int Special=2;
        // Cantidad de letras mayusculas en la contrasenia
        int uppercaseCounter=0;
        // Cantidad de letras minusculas en la contrasenia
        int lowercaseCounter=0;
        // cantidad de digitos en la contrasenia
        int digitCounter=0;
        // cantidad de caracteres especiales en la contrasenia
        int specialCounter=0;
        int contadorDeIntentos = 0 ;

        // Tomar desde consola el input del usuario

        System.out.println("Ingrese la contraseña : \n");

        Scanner input_usuario = new Scanner(System.in);

        // Tomar desde consola el input del usuario a y guardarlo como una contraseña en un string

        String contraseña = input_usuario.nextLine();

        for (int i=0; i < contraseña.length(); i++ ) {
            char c = contraseña.charAt(i);
            if(Character.isUpperCase(c))
                uppercaseCounter++;
            else if(Character.isLowerCase(c))
                lowercaseCounter++;
            else if(Character.isDigit(c))
                digitCounter++;
            if(c>=33&&c<=46||c==64){
                specialCounter++;
            }

        }

        if (contraseña.length() >= MAX && uppercaseCounter >= MIN_Uppercase
                && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
            System.out.println("Contaseña valida");
        }
        else {
            System.out.println("Su contraseña no cumple con los parametros:");
            if(contraseña.length() < MAX)
                System.out.println(" Al menos 8 caracteres");
            if (lowercaseCounter < MIN_Lowercase)
                System.out.println("Minima cantidad de letras en minsucula");
            if (uppercaseCounter < MIN_Uppercase)
                System.out.println("Minima cantidad de letras en mayuscula");
            if(digitCounter < NUM_Digits)
                System.out.println("Minima cantidad de digitos");
            if(specialCounter < Special)
                System.out.println("La Contaseña debe contener al menos 3 caracteres especiales");

            while(!contraseña.equals(input_usuario) && contadorDeIntentos < 3) {
                contadorDeIntentos ++ ;
                contraseña = input_usuario.nextLine();
                if (contraseña.equals(input_usuario)) {
                    System.out.println("Contaseña valida");
                    break;
                }

            }
            System.out.println("Contaseña Bloqueada");
        }




    }
}

