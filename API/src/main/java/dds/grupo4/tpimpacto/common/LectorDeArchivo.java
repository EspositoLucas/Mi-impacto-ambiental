package dds.grupo4.tpimpacto.common;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LectorDeArchivo {
    public List<String> leerLineas(String rutaResource) {
        URL resource = getClass().getClassLoader().getResource(rutaResource);
        if (resource == null) {
            throw new IllegalArgumentException("No se encontro el recurso especificado en rutaResource (" + rutaResource + ")");
        }

        try {
            File file = new File(resource.toURI());
            List<String> lines = Files.readAllLines(file.toPath());
            return lines
                    .stream()
                    .map(String::trim) // Saco espacios al principio y al final de cada linea
                    .filter(s -> !s.isEmpty()) // Borro las lineas que estan vacias
                    .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
