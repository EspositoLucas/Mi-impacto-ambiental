package dds.grupo4.tpimpacto;

import dds.grupo4.tpimpacto.controllers.MiembroControllerFake;
import dds.grupo4.tpimpacto.controllers.OrganizacionControllerFake;
import dds.grupo4.tpimpacto.controllers.TipoServicioContratadoControllerFake;
import dds.grupo4.tpimpacto.controllers.TransportePublicoControllerFake;
import dds.grupo4.tpimpacto.extras.ConsoleHelper;
import dds.grupo4.tpimpacto.extras.OperacionTesteo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class SpringCommandLineRunner implements CommandLineRunner {
    // Todos estos Controllers son "Fakes" porque unicamente estan para que no quede toda la logica en el Main,
    // y quede el codigo separado segun cada entidad (Organizacion, Miembro...). En un futuro habria que convertir
    // los Controllers a RestController, para usarlos en la API
    private final OrganizacionControllerFake organizacionControllerFake;
    private final MiembroControllerFake miembroControllerFake;
    private final TipoServicioContratadoControllerFake tipoServicioContratadoControllerFake;
    private final TransportePublicoControllerFake transportePublicoControllerFake;

    // Todos estos parametros los "inyecta" Spring directamente, no hay que pasarselos porque los configura solos
    @Autowired
    public SpringCommandLineRunner(OrganizacionControllerFake organizacionControllerFake, MiembroControllerFake miembroControllerFake, TipoServicioContratadoControllerFake tipoServicioContratadoControllerFake, TransportePublicoControllerFake transportePublicoControllerFake) {
        this.organizacionControllerFake = organizacionControllerFake;
        this.miembroControllerFake = miembroControllerFake;
        this.tipoServicioContratadoControllerFake = tipoServicioContratadoControllerFake;
        this.transportePublicoControllerFake = transportePublicoControllerFake;
    }

    @Override
    public void run(String... args) throws Exception {
        OperacionTesteo operacionTesteo;
        do {
            operacionTesteo = mostrarOperacionesYElegir();
            switch (operacionTesteo) {
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
                case CREAR_TIPO_SERVICIO_CONTRATADO:
                    tipoServicioContratadoControllerFake.crearTipoServicioContratado();
                    break;
                case CREAR_TRANSPORTE_PUBLICO:
                    transportePublicoControllerFake.crearLinea();
                    break;
            }

            ConsoleHelper.print("\n\n");
        } while (!operacionTesteo.equals(OperacionTesteo.EXIT));

        ConsoleHelper.printLine("Chauchissss!!!");

        ConsoleHelper.closeScanner();

        System.exit(0);
    }

    private OperacionTesteo mostrarOperacionesYElegir() {
        while (true) {
            mostrarOperaciones();
            String input = ConsoleHelper.readString();
            try {
                int operacion = Integer.parseInt(input);
                return OperacionTesteo.valueOf(operacion);
            } catch (Exception e) {
                ConsoleHelper.printLine("No te pases de vivarache, mete algo valido");
                ConsoleHelper.printLine();
            }
        }
    }

    private void mostrarOperaciones() {
        ConsoleHelper.printLine("3- Cargar mediciones");
        ConsoleHelper.printLine("4- Crear organizacion");
        ConsoleHelper.printLine("5- Listar organizaciones");
        ConsoleHelper.printLine("6- Crear miembro");
        ConsoleHelper.printLine("7- Aceptar solicitud");
        ConsoleHelper.printLine("8- Listar miembros de una organizacion");
        ConsoleHelper.printLine("9- Crear tipo de servicio contratado");
        ConsoleHelper.printLine("10- Crear transporte publico");
        ConsoleHelper.printLine("11- Salir");
        ConsoleHelper.print("Elegi una opcion: ");
    }
}
