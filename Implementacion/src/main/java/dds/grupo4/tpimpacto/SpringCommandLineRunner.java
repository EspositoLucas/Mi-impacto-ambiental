package dds.grupo4.tpimpacto;

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

    // Todos estos parametros los "inyecta" Spring directamente, no hay que pasarselos porque los configura solos
    @Autowired
    public SpringCommandLineRunner() {
    }

    @Override
    public void run(String... args) {
        OperacionTesteo operacionTesteo;
        do {
            operacionTesteo = mostrarOperacionesYElegir();
            switch (operacionTesteo) {
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
