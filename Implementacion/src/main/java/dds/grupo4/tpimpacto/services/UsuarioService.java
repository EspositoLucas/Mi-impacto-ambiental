package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.LoginRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioResponse;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioService extends BaseService<Usuario> {
    ResponseEntity<RegistrarUsuarioResponse> register(RegistrarUsuarioRequest request);

    ResponseEntity<BaseResponse> login(LoginRequest request);

    Optional<Usuario> getUsuarioPorUsername(String username);

    boolean existeUsuarioConUsername(String username);
}
