package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    // En algun momento de la vida esta lista va a salir de una BD
    private final List<Usuario> usuariosRegistrados = new ArrayList<>(Arrays.asList(
            new Usuario("echi", "!echi!"),
            new Usuario("mili", "!mili!"),
            new Usuario("roni", "!roni!"),
            new Usuario("lucas", "!lucas!"),
            new Usuario("ziro", "!ziro!"),
            new Usuario("agus", "!agus!"))
    );

    @Override
    public Optional<Usuario> getByUsername(String username) {
        return usuariosRegistrados.stream()
                .filter(usuario -> usuario.getUsername().equals(username))
                .findAny();
    }

    @Override
    public void save(Usuario user) {
        usuariosRegistrados.add(user);
    }

}
