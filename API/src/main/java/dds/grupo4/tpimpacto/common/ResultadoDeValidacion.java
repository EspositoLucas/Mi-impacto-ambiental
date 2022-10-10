package dds.grupo4.tpimpacto.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ResultadoDeValidacion {

    private boolean valido;
    private List<String> errores;

    public ResultadoDeValidacion(boolean valido, List<String> errores) {
        this.valido = valido;
        this.errores = errores;
    }

    public ResultadoDeValidacion(boolean valido, String error) {
        this(valido, Arrays.asList(error));
    }

    public List<String> getErrores() {
        if (valido)
            throw new RuntimeException("No se puede llamar a getErrores() en un ResultadoDeValidacion que es valido");
        return errores;
    }

    public String getErroresEnLineas() {
        return getErrores().stream()
                .map(error -> "- " + error + "\n")
                .collect(Collectors.joining());
    }

}
