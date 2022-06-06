package dds.grupo4.tpimpacto;

import dds.grupo4.tpimpacto.common.LectorDeArchivoImpl;
import dds.grupo4.tpimpacto.common.ResultadoDeValidacion;
import dds.grupo4.tpimpacto.common.ValidadorContrasenia;
import dds.grupo4.tpimpacto.controllers.MiembroControllerFake;
import dds.grupo4.tpimpacto.controllers.OrganizacionControllerFake;
import dds.grupo4.tpimpacto.entities.Usuario;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.extras.OperacionTesteo;
import dds.grupo4.tpimpacto.repositories.*;
import dds.grupo4.tpimpacto.services.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootApplication
public class Main {

    private static final int MAX_INTENTOS_REGISTRO = 3;

    private static final SectorRepository sectorRepository = new SectorRepositoryImpl();
    private static final OrganizacionRepository organizacionRepository = new OrganizacionRepositoryImpl();
    private static final UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
    private static final MiembroRepository miembroRepository = new MiembroRepositoryImpl();

    private static final SectorService sectorService = new SectorServiceImpl(sectorRepository);
    private static final OrganizacionService organizacionService = new OrganizacionServiceImpl(organizacionRepository, sectorService);
    private static final UsuarioService usuarioService = new UsuarioServiceImpl(usuarioRepository);
    private static final MiembroService miembroService = new MiembroServiceImpl(miembroRepository);

    private static final OrganizacionControllerFake organizacionControllerFake = new OrganizacionControllerFake(organizacionService);
    private static final MiembroControllerFake miembroControllerFake = new MiembroControllerFake(miembroService, organizacionService, sectorService);

    public static void main(String[] args) throws Exception {
        /*
         * Descomentar esto cuando querramos usar Spring para hacer la API loca
         * SpringApplication.run(Main.class, args);
         */

        OperacionTesteo operacionTesteo;
        do {
            operacionTesteo = mostrarOperacionesYElegir();
            switch (operacionTesteo) {
                case REGISTRO:
                    registrarUsuario();
                    break;
                case LOGIN:
                    logearse();
                    break;
                case CARGAR_MEDICIONES:
                    organizacionControllerFake.cargarMediciones();
                    break;
                case CREAR_ORGANIZACION:
                    organizacionControllerFake.crearOrganizacion();
                    break;
                case LISTAR_ORGANIZACIONES:
                    organizacionControllerFake.listarOrganizaciones();
                    break;
                case CREAR_MIEMBRO:
                    miembroControllerFake.crearMiembro();
                    break;
                case ACEPTAR_SOLICITUD:
                    organizacionControllerFake.aceptarSolicitud();
                    break;
                case LISTAR_MIEMBROS_DE_ORGANIZACION:
                    organizacionControllerFake.listarMiembros();
                    break;
            }

            ConsoleHelper.printLine("\n");
        } while (!operacionTesteo.equals(OperacionTesteo.EXIT));

        ConsoleHelper.closeScanner();
    }

    public static OperacionTesteo mostrarOperacionesYElegir() {
        while (true) {
            mostrarOperaciones();
            String input = ConsoleHelper.readString();
            try {
                int operacion = Integer.parseInt(input);
                OperacionTesteo operacionTesteo = OperacionTesteo.valueOf(operacion);
                return operacionTesteo;
            } catch (Exception e) {
                ConsoleHelper.printLine("No te pases de vivarache, mete algo valido");
                ConsoleHelper.printLine();
            }
        }
    }

    private static void mostrarOperaciones() {
        ConsoleHelper.printLine("1- Login");
        ConsoleHelper.printLine("2- Registrar usuario");
        ConsoleHelper.printLine("3- Cargar mediciones");
        ConsoleHelper.printLine("4- Crear organizacion");
        ConsoleHelper.printLine("5- Listar organizaciones");
        ConsoleHelper.printLine("6- Crear miembro");
        ConsoleHelper.printLine("7- Aceptar solicitud");
        ConsoleHelper.printLine("8- Listar miembros de una organizacion");
        ConsoleHelper.printLine("9- Salir");
        ConsoleHelper.print("Elegi una opcion: ");
    }

    public static void registrarUsuario() throws Exception {
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
            if (resultado.esValido()) {
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

    public static void logearse() {
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
            ConsoleHelper.printLine("Usuario " + user.getUsername() + " logeado joyita");
            user.logeoCorrecto();
        } else {
            ConsoleHelper.printLine("Contrasenia incorrecta!");
            user.logeoIncorrecto();
            if (user.estaBloqueado()) {
                ConsoleHelper.printLine("Estas bloqueado hasta la fecha " + formatearBloqueadoHasta(user.getBloqueadoHasta()));
            }
        }
    }

    public static ResultadoDeValidacion validarNuevaContrasenia(String nuevaPassword) {
        ValidadorContrasenia validador = new ValidadorContrasenia(new LectorDeArchivoImpl());
        return validador.validarContrasenia(nuevaPassword);
    }

    public static String formatearBloqueadoHasta(LocalDateTime bloqueadoHasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return bloqueadoHasta.format(formatter);
    }

}
