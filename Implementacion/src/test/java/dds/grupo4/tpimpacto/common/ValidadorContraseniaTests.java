package dds.grupo4.tpimpacto.common;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@CustomTestAnnotation
@FastTests
public class ValidadorContraseniaTests {

    @Mock
    private LectorDeArchivo mockLector;

    private ValidadorContrasenia validadorContrasenia;

    @BeforeEach
    public void setUp() {
        List<String> contraseniasInseguras = new ArrayList<>(
                Arrays.asList("masUsada01", "UNAcontraSEGURAperoMASusada123!!**")
        );
        when(mockLector.leerLineas(ValidadorContrasenia.RUTA_ARCHIVO_CONTRASENIAS_INSEGURAS))
                .thenReturn(contraseniasInseguras);
        validadorContrasenia = new ValidadorContrasenia(mockLector);
    }

    @Test
    public void validarContrasenia_cuandoLaContraseniaEstaEnLaListaDeMasUsadas_retornaError() {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia("UNAcontraSEGURAperoMASusada123!!**");
        Assertions.assertFalse(resultado.isValido());
    }

    @Test
    public void validarContrasenia_cuandoLaContraseniaNoEstaEnLaListaDeMasUsadas_retornaOk() {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia("UNAcontraQueNoEsMasUsadaYPasaLosTests123!!**");
        Assertions.assertTrue(resultado.isValido());
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
        Assertions.assertFalse(resultado.isValido());
    }

    @Test
    public void validarContrasenia_cuandoLaContraCumpleTodosLosRequisitos_retornaOk() {
        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia("ESTAesMIcontraseniaSUPERsegura12345!*[]{}");
        Assertions.assertTrue(resultado.isValido());
    }

}
