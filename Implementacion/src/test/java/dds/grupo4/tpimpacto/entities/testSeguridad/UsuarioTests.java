package dds.grupo4.tpimpacto.entities.testSeguridad;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioTests {

    @Test
    public void validarContrasenia_cuandoLaContraseniaEsIncorrecta_retornaFalse() {
        Usuario user = new Usuario("username", "password");

        boolean passCorrecta = user.validarContrasenia("algoRaro");

        Assertions.assertFalse(passCorrecta);
    }

    @Test
    public void validarContrasenia_cuandoLaContraseniaEsCorrecta_retornaTrue() {
        Usuario user = new Usuario("username", "password");

        boolean passCorrecta = user.validarContrasenia("password");

        Assertions.assertTrue(passCorrecta);
    }

    @Test
    public void logeoIncorrecto_cuandoSeSuperaElLimiteDeIntentosParaLogearse_bloqueaAlUsuario() {
        Usuario user = new Usuario("username", "password");
        bloquearUsuario(user);

        boolean userEstaBloqueado = user.estaBloqueado();

        Assertions.assertTrue(userEstaBloqueado);
    }

    private void bloquearUsuario(Usuario user) {
        for (int i = 0; i <= Usuario.INTENTOS_DE_LOGEO_PERMITIDOS; i++) {
            user.logeoIncorrecto();
        }
    }

}
