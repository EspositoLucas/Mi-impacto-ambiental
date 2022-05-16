package validador.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidadorContraseniaTests {

    private LectorDeArchivo mockLector;
    private ValidadorContrasenia validadorContrasenia;

    @BeforeAll
    public void initialSetUp() {
        mockLector = mock(LectorDeArchivo.class);
        when(mockLector.leerLineas(ValidadorContrasenia.RUTA_ARCHIVO_CONTRASENIAS_INSEGURAS)).thenReturn(Arrays.asList("masUsada01, masUsada02, masUsada03"));
    }

    @BeforeEach
    public void setUp() {
        validadorContrasenia = new ValidadorContrasenia(mockLector);
    }

    @Test
    public void validarContrasenia_cuandoLaContraseniaEstaEnLaListaDeMasUsadas_retornaError() {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia("masUsada01");
        Assertions.assertFalse(resultado.esValido());
    }

    @Test
    public void validarContrasenia_cuandoLaContraseniaNoEstaEnLaListaDeMasUsadas_retornaOk() {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia("UNAcontraQueNoEsMasUsadaYPasaLosTests123!!**");
        Assertions.assertTrue(resultado.esValido());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abc", // Pocos caracteres
            "abcdefghijkl", // Le faltan mayusculas, digitos y simbolos
            "ABCDefghIJKLmnop", // Le faltan digitos y simbolos
            "ABCDefgh1234" // Le faltan simbolos
    })
    public void validarContrasenia_cuandoLaContraNoCumpleRequisitos_retornaError(String contraseniaParam) {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia(contraseniaParam);
        Assertions.assertFalse(resultado.esValido());
    }

    @Test
    public void validarContrasenia_cuandoLaContraCumpleTodosLosRequisitos_retornaOk() {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia("ESTAesMIcontraseniaSUPERsegura12345!*[]{}");
        Assertions.assertTrue(resultado.esValido());
    }

}
