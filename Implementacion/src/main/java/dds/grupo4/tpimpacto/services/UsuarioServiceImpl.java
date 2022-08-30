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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {

    private final MiembroRepository miembroRepository;
    private final ValidadorContrasenia validadorContrasenia;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, MiembroRepository miembroRepository, ValidadorContrasenia validadorContrasenia) {
        super(usuarioRepository);
        this.miembroRepository = miembroRepository;
        this.validadorContrasenia = validadorContrasenia;
    }

    @Override
    @Transactional
    public ResponseEntity<RegistrarUsuarioResponse> register(RegistrarUsuarioRequest request) {
        if (existeUsuarioConUsername(request.getUsername())) {
            RegistrarUsuarioResponse response = new RegistrarUsuarioResponse("Ya existe otro usuario con ese username");
            return ResponseEntity.badRequest().body(response);
        }

        ResultadoDeValidacion resultado = validadorContrasenia.validarContrasenia(request.getPassword());
        if (!resultado.isValido()) {
            RegistrarUsuarioResponse response = new RegistrarUsuarioResponse(
                    "Su contrasenia no cumple con los requisitos minimos",
                    resultado.getErrores()
            );
            return ResponseEntity.badRequest().body(response);
        }

        // Ver de hashear la contrasenia antes de guardar el Usuario
        Usuario nuevoUsuario = new Usuario(request.getUsername(), request.getPassword());

        if (request.getIdMiembro() != null) {
            Miembro miembro = miembroRepository.getById(request.getIdMiembro());
            if (miembro == null) {
                RegistrarUsuarioResponse response = new RegistrarUsuarioResponse("No se encontro al Miembro con el ID especificado");
                return ResponseEntity.badRequest().body(response);
            }
            miembro.setUsuario(nuevoUsuario);
        }

        this.save(nuevoUsuario);
        RegistrarUsuarioResponse response = new RegistrarUsuarioResponse("Usuario creado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<BaseResponse> login(LoginRequest request) {
        Optional<Usuario> optionalUsuario = repository.getByUsername(request.getUsername());
        if (!optionalUsuario.isPresent()) {
            BaseResponse response = new BaseResponse("No existe ningun usuario con el username especificado");
            return ResponseEntity.badRequest().body(response);
        }

        Usuario user = optionalUsuario.get();

        if (user.estaBloqueado()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            BaseResponse response = new BaseResponse(
                    "El usuario esta bloqueado hasta la fecha " + user.getBloqueadoHasta().format(formatter)
            );
            return ResponseEntity.status(HttpStatus.LOCKED).body(response);
        }

        if (!user.getPassword().equals(request.getPassword())) {
            user.logeoIncorrecto();
            BaseResponse response = new BaseResponse("Contrasenia incorrecta");
            return ResponseEntity.badRequest().body(response);
        }

        // TODO: implementar JWTs
        user.logeoCorrecto();
        BaseResponse response = new BaseResponse("OK");
        return ResponseEntity.ok(response);
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
