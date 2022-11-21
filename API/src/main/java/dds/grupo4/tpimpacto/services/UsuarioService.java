package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.common.ResultadoDeValidacion;
import dds.grupo4.tpimpacto.common.ValidadorContrasenia;
import dds.grupo4.tpimpacto.dtos.LoginRequest;
import dds.grupo4.tpimpacto.dtos.LoginResponse;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import dds.grupo4.tpimpacto.security.JwtUtils;
import dds.grupo4.tpimpacto.services.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {

    private final SolicitudRepository solicitudRepository;
    private final ValidadorContrasenia validadorContrasenia;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UsuarioService(UsuarioRepository usuarioRepository, SolicitudRepository solicitudRepository, SectorRepository sectorRepository, ValidadorContrasenia validadorContrasenia, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        super(usuarioRepository);
        this.solicitudRepository = solicitudRepository;
        this.validadorContrasenia = validadorContrasenia;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public RegistrarUsuarioResponse register(RegistrarUsuarioRequest request) {
        if (existeUsuarioConUsername(request.getUsername())) {
            return new RegistrarUsuarioResponse(HttpStatus.BAD_REQUEST, "Ya existe otro usuario con ese username");
        }

        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia(request.getPassword());
        if (!resultado.isValido()) {
            return new RegistrarUsuarioResponse(
                    HttpStatus.BAD_REQUEST,
                    "Su contrasenia no cumple con los requisitos minimos",
                    resultado.getErrores()
            );
        }

        Solicitud solicitud = solicitudRepository.getById(request.getIdSolicitud());
        if (solicitud == null) {
            return new RegistrarUsuarioResponse(HttpStatus.BAD_REQUEST, "No se encontro la Solicitud con el ID especificado");
        }

        Miembro miembro = solicitud.getMiembro();
        Usuario nuevoUsuario = crearUsuario(request.getUsername(), request.getPassword(), false);
        miembro.setUsuario(nuevoUsuario);
        return new RegistrarUsuarioResponse(HttpStatus.CREATED, "Usuario creado exitosamente");
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Optional<Usuario> optionalUsuario = repository.getByUsername(request.getUsername());
        if (!optionalUsuario.isPresent()) {
            return new LoginResponse(HttpStatus.BAD_REQUEST, "No existe ningun usuario con el username especificado");
        }

        Usuario user = optionalUsuario.get();
        if (user.estaBloqueado()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return new LoginResponse(
                    HttpStatus.LOCKED,
                    "El usuario esta bloqueado hasta la fecha " + user.getBloqueadoHasta().format(formatter)
            );
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.logeoIncorrecto();
            return new LoginResponse(HttpStatus.BAD_REQUEST, "Contrasenia incorrecta");
        }

        user.logeoCorrecto();
        String accessToken = jwtUtils.generateToken(user);
        LoginResponse response = new LoginResponse(HttpStatus.OK, "Guardar el `accessToken` y mandarlo en cada request");
        response.setAccessToken(accessToken);
        return response;
    }

    @Transactional
    public boolean existeUsuarioConUsername(String username) {
        return repository.getByUsername(username).isPresent();
    }

    @Transactional
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return repository.getByUsername(username);
    }

    public Usuario crearUsuario(String username, String password, boolean admin) {
        Usuario usuario = new Usuario(username, passwordEncoder.encode(password));
        usuario.setAdmin(admin);
        return usuario;
    }

}
