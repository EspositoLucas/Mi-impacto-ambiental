package dds.grupo4.tpimpacto.security;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtils jwtUtils;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository, JwtUtils jwtUtils) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.getByUsername(username);
        if (!optionalUsuario.isPresent())
            throw new UsernameNotFoundException("User " + username + " not found.");

        Usuario usuario = optionalUsuario.get();
        List<GrantedAuthority> authorities = jwtUtils.getAuthorities(usuario);
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

}
