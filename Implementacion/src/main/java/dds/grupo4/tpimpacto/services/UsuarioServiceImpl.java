package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.Usuario;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuarioConUsername(String username) {
        return usuarioRepository.getByUsername(username).isPresent();
    }

    @Override
    public void agregarUsuario(Usuario user) {
        usuarioRepository.addUser(user);
    }

    @Override
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return usuarioRepository.getByUsername(username);
    }

}
