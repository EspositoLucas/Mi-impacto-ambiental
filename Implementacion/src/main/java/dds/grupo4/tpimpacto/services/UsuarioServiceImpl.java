package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.Usuario;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuarioConUsername(String username) {
        return usuarioRepository.getByUsername(username).isPresent();
    }

    @Override
    public void save(Usuario user) {
        if (usuarioRepository.getAll().contains(user)) {
            usuarioRepository.update(user);
        } else {
            usuarioRepository.save(user);
        }
    }

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.getAll();
    }

    @Override
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return usuarioRepository.getByUsername(username);
    }

}
