package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;

import java.util.Optional;

public interface UsuarioService extends BaseService<Usuario> {
    Optional<Usuario> getUsuarioPorUsername(String username);

    boolean existeUsuarioConUsername(String username);
}
