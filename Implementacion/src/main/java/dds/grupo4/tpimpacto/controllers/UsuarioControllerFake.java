package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.common.LectorDeArchivoImpl;
import dds.grupo4.tpimpacto.common.ResultadoDeValidacion;
import dds.grupo4.tpimpacto.common.ValidadorContrasenia;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.services.UsuarioService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class UsuarioControllerFake {

    private final int MAX_INTENTOS_REGISTRO = 3;

    private final UsuarioService usuarioService;

    public UsuarioControllerFake(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void registrarUsuario() throws Exception {
        String username;
        boolean usernameCorrecto = false;
        do {
            ConsoleHelper.print("Ingresa el nuevo username: ");
            username = ConsoleHelper.readString();
            if (usuarioService.existeUsuarioConUsername(username)) {
                ConsoleHelper.printLine("Ya existe otro usuario con ese username!");
            } else {
                usernameCorrecto = true;
            }
        } while (!usernameCorrecto);

        String password;
        boolean passwordCorrecta = false;
        int intentosFallidos = 0;
        do {
            ConsoleHelper.print("Ingresa la nueva contrasenia: ");
            password = ConsoleHelper.readString();
            ResultadoDeValidacion resultado = validarNuevaContrasenia(password);
            if (resultado.isValido()) {
                Usuario nuevoUser = new Usuario(username, password);
                usuarioService.save(nuevoUser);
                ConsoleHelper.printLine("Usuario creado exitosamente!");
                passwordCorrecta = true;
            } else {
                intentosFallidos++;
                ConsoleHelper.printLine("Contrasenia invalida:-");
                ConsoleHelper.printLine(resultado.getErroresEnLineas());
                if (intentosFallidos > MAX_INTENTOS_REGISTRO) {
                    ConsoleHelper.printLine("Demasiados errores");
                }
            }
        } while (!passwordCorrecta && intentosFallidos <= MAX_INTENTOS_REGISTRO);
    }

    public void logearse() {
        ConsoleHelper.print("Ingrese su usuario: ");
        String username = ConsoleHelper.readString();

        Optional<Usuario> optionalUser = usuarioService.getUsuarioPorUsername(username);
        if (!optionalUser.isPresent()) {
            ConsoleHelper.printLine("Usuario incorrecto!");
            return;
        }

        Usuario user = optionalUser.get();
        if (user.estaBloqueado()) {
            ConsoleHelper.printLine("Estas bloqueado hasta la fecha " + formatearBloqueadoHasta(user.getBloqueadoHasta()));
            return;
        }

        ConsoleHelper.print("Ingrese la contraseña: ");
        String password = ConsoleHelper.readString();
        if (user.validarContrasenia(password)) {
            user.logeoCorrecto();
            ConsoleHelper.printLine("Usuario " + user.getUsername() + " logeado joyita");
        } else {
            user.logeoIncorrecto();
            ConsoleHelper.printLine("Contrasenia incorrecta! (intentos: " + user.getCantidadIntentosIncorrectos() + ")");
            if (user.estaBloqueado()) {
                ConsoleHelper.printLine("Estas bloqueado hasta la fecha " + formatearBloqueadoHasta(user.getBloqueadoHasta()));
            }
        }
    }

    private ResultadoDeValidacion validarNuevaContrasenia(String nuevaPassword) {
        ValidadorContrasenia validador = new ValidadorContrasenia(new LectorDeArchivoImpl());
        return validador.validarContrasenia(nuevaPassword);
    }

    private String formatearBloqueadoHasta(LocalDateTime bloqueadoHasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return bloqueadoHasta.format(formatter);
    }

}
