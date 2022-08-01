package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }

    @Override
    @Transactional
    public boolean existeUsuarioConUsername(String username) {
        return repository.getByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return repository.getByUsername(username);
    }

}
