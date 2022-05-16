package validador.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResultadoDeValidacion {

    private boolean esValido;
    private List<String> errores;

    public ResultadoDeValidacion(boolean esValido, List<String> errores) {
        this.esValido = esValido;
        this.errores = errores;
    }

    public ResultadoDeValidacion(boolean esValido, String error) {
        this(esValido, Arrays.asList(error));
    }

    public boolean esValido() {
        return esValido;
    }

    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    public List<String> getErrores() throws Exception {
        if (esValido)
            throw new Exception();
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public String getErroresEnLineas() throws Exception {
        return getErrores()
                .stream()
                .map(error -> "- " + error + "\n")
                .collect(Collectors.joining());
    }

}
