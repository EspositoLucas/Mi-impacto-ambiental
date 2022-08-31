package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.common.ResultadoDeValidacion;
import dds.grupo4.tpimpacto.common.ValidadorContrasenia;
import dds.grupo4.tpimpacto.dtos.LoginRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioResponse;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import dds.grupo4.tpimpacto.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioRepository> {

    private final MiembroRepository miembroRepository;
    private final ValidadorContrasenia validadorContrasenia;

    public UsuarioService(UsuarioRepository usuarioRepository, MiembroRepository miembroRepository, ValidadorContrasenia validadorContrasenia) {
        super(usuarioRepository);
        this.miembroRepository = miembroRepository;
        this.validadorContrasenia = validadorContrasenia;
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

        Miembro miembro = miembroRepository.getById(request.getIdMiembro());
        if (miembro == null) {
            return new RegistrarUsuarioResponse(HttpStatus.BAD_REQUEST, "No se encontro al Miembro con el ID especificado");
        }

        // Ver de hashear la contrasenia antes de guardar el Usuario
        Usuario nuevoUsuario = new Usuario(request.getUsername(), request.getPassword());
        miembro.setUsuario(nuevoUsuario);

        this.save(nuevoUsuario);
        return new RegistrarUsuarioResponse(HttpStatus.CREATED, "Usuario creado exitosamente");
    }

    @Transactional
    public BaseResponse login(LoginRequest request) {
        Optional<Usuario> optionalUsuario = repository.getByUsername(request.getUsername());
        if (!optionalUsuario.isPresent()) {
            return new BaseResponse(HttpStatus.BAD_REQUEST, "No existe ningun usuario con el username especificado");
        }

        Usuario user = optionalUsuario.get();

        if (user.estaBloqueado()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return new BaseResponse(
                    HttpStatus.LOCKED,
                    "El usuario esta bloqueado hasta la fecha " + user.getBloqueadoHasta().format(formatter)
            );
        }

        if (!user.getPassword().equals(request.getPassword())) {
            user.logeoIncorrecto();
            return new BaseResponse(HttpStatus.BAD_REQUEST, "Contrasenia incorrecta");
        }

        // TODO: implementar JWTs
        user.logeoCorrecto();
        return new BaseResponse(HttpStatus.OK, "OK");
    }

    @Transactional
    public boolean existeUsuarioConUsername(String username) {
        return repository.getByUsername(username).isPresent();
    }

    @Transactional
    public Optional<Usuario> getUsuarioPorUsername(String username) {
        return repository.getByUsername(username);
    }

}
