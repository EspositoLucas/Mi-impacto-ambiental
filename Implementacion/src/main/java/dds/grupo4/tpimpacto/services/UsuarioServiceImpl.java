package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public boolean existeUsuarioConUsername(String username) {
        return usuarioRepository.getByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public void save(Usuario user) {
        if (usuarioRepository.getAll().contains(user)) {
            usuarioRepository.merge(user);
        } else {
            usuarioRepository.save(user);
        }
    }

    @Override
    @Transactional
    public List<Usuario> getAll() {
        return usuarioRepository.getAll();
    }

    @Override
    @Transactional
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return usuarioRepository.getByUsername(username);
    }

}
