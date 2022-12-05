package dds.grupo4.tpimpacto.common;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LectorDeArchivo {
    public List<String> leerLineas(String rutaResource) {
        try (InputStream in = getClass().getResourceAsStream(rutaResource)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            List<String> lines = reader.lines().collect(Collectors.toList());
            return lines
                    .stream()
                    .map(String::trim) // Saco espacios al principio y al final de cada linea
                    .filter(s -> !s.isEmpty()) // Borro las lineas que estan vacias
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
