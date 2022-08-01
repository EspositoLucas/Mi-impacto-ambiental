package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@CustomTestAnnotation
public class UsuarioRepositoryTests {

    private final UsuarioRepository usuarioRepository;

    private Usuario usuarioTest;

    @Autowired
    public UsuarioRepositoryTests(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @BeforeEach
    public void buildUsuarioTest() {
        usuarioTest = new Usuario("userTest", "passTest");
    }

    @Test
    @Transactional
    public void getByUsername_cuandoExisteUsuarioConUsername_retornaAlUsuario() {
        usuarioRepository.save(usuarioTest);

        Optional<Usuario> optionalUsuarioDeBD = usuarioRepository.getByUsername("userTest");

        Assertions.assertTrue(optionalUsuarioDeBD.isPresent());
        Assertions.assertEquals("userTest", optionalUsuarioDeBD.get().getUsername());
        Assertions.assertEquals("passTest", optionalUsuarioDeBD.get().getPassword());
    }

    @Test
    @Transactional
    public void getByUsername_cuandoNoExisteUsuarioConUsername_retornaEmptyOptional() {
        Optional<Usuario> optionalUsuario = usuarioRepository.getByUsername("userQueNoExiste");

        Assertions.assertFalse(optionalUsuario.isPresent());
    }
}
