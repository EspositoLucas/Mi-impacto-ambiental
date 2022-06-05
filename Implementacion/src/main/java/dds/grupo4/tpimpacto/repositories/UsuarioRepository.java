package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> getByUsername(String username);

    void addUser(Usuario user);
}
