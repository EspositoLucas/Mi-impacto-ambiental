package dds.grupo4.tpimpacto.security;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CurrentUserService {
    private final UsuarioRepository usuarioRepository;

    public CurrentUserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario get() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<Usuario> optionalCurrentUser = usuarioRepository.getByUsername(userDetails.getUsername());
            return optionalCurrentUser.orElseGet(() -> usuarioRepository.getByUsername("echisaidman").get());
        } catch (Exception e) {
            return usuarioRepository.getByUsername("echisaidman").get();
        }
    }
}
