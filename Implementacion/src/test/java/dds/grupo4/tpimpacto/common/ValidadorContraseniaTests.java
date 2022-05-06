package dds.grupo4.tpimpacto.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void contrasenia_esta_dentro_del_top_10000_peores_contrasenias() {
        ResultadoDeValidacion resultado = validadorContrasenia.validar("masUsada01");
        Assertions.assertFalse(resultado.esValido());
    }
//    public void contrasenia_no_esta_dentro_del_top_10000_peores_contrasenias() {
//        Assert.assertTrue(esValido: true);
//    }
//
//    //Tests para corroborar si una contrasenia que no pertenece a la lista de las 100000 peores contrasenias cumple con las condiciones para ser segura
//
//    public void contrasenia_contiene_cantidad_minima_de_caracteres() {
//        Assert.assertTrue(esValido: true);
//    }
//    public void contrasenia_no_contiene_cantidad_minima_de_caracteres() {
//        Assert.assertEquals(1,1);
//    }
//
//    public void contrasenia_contiene_cantidad_minima_de_letras_minusculas() {
//        Assert.assertEquals(1,1);
//    }
//
//    public void contrasenia_no_contiene_cantidad_minima_de_letras_minusculas() {
//        Assert.assertEquals(1,1);
//    }
//
//    public void contrasenia_contiene_cantidad_minima_de_letras_mayusculas() {
//        Assert.assertEquals(1,1);
//    }
//
//    public void contrasenia_no_contiene_cantidad_minima_de_letras_mayusculas() {
//        Assert.assertEquals(1,1);
//    }
//
//    public void contrasenia_contiene_cantidad_minima_de_digitos() {
//        Assert.assertEquals(1,1);
//    }
//    public void contrasenia_no_contiene_cantidad_minima_de_digitos() {
//        Assert.assertEquals(1,1);
//    }
//
//    public void contrasenia_contiene_cantidad_minima_de_caracteres_especiales() {
//        Assert.assertEquals(1,1);
//    }
//    public void contrasenia_no_contiene_cantidad_minima_de_caracteres_especiales() {
//        Assert.assertEquals(1,1);
//    }


}
