package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {
    void save(Usuario user);

    Optional<Usuario> getUsuarioPorUsername(String username);

    boolean existeUsuarioConUsername(String username);
}
