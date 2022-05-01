package validador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private static final int MAX_INTENTOS = 3;

    private static final List<Usuario> usuariosRegistrados = Arrays.asList(
            new Usuario("echito", "!echito!"),
            new Usuario("milita", "!milita!"),
            new Usuario("ronito", "!ronito!"),
            new Usuario("lukitas", "!lukitas!"),
            new Usuario("zirito", "!zirito!"),
            new Usuario("agus", "!agus!")
    );

    public static void main(String[] args) throws Exception {

        boolean usuarioLogeado = false;
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.print("Ingrese su usuario: ");
            final String username = scanner.nextLine();

            final Optional<Usuario> optionalUser = getUsuarioPorUsername(username);
            if (!optionalUser.isPresent()) {
                System.out.println("Usuario incorrecto!\n\n");
                continue;
            }

            final Usuario user = optionalUser.get();
            if (user.estaBloqueado()) {
                System.out.println("Estas bloqueado hasta la fecha " + formatearBloqueadoHasta(user.getBloqueadoHasta()));
                continue;
            }

            System.out.print("Ingrese la contraseña: ");
            final String password = scanner.nextLine();
            if (user.validarContrasenia(password)) {
                System.out.println("Usuario " + user.getUsername() + " logeado de perlitass!");
                user.logeoCorrecto();
                usuarioLogeado = true;
            } else {
                System.out.println("Contrasenia incorrecta!");
                user.logeoIncorrecto();
                if (user.estaBloqueado()) {
                    System.out.println("Estas bloqueado hasta la fecha " + formatearBloqueadoHasta(user.getBloqueadoHasta()));
                    continue;
                }
            }

        } while (!usuarioLogeado);

        /*
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
                if (formula >= 5) {
                    timer.schedule(new NewLine(), 0, formula * 1000);
                    contrasenia = scanner.nextLine();
                }
            }
        } while (!resultado.esValido());
        */
    }

    public static Optional<Usuario> getUsuarioPorUsername(String username) {
        return usuariosRegistrados.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public static String formatearBloqueadoHasta(LocalDateTime bloqueadoHasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return bloqueadoHasta.format(formatter);
    }

}
