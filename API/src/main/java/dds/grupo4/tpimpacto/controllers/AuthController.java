package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.LoginRequest;
import dds.grupo4.tpimpacto.dtos.LoginResponse;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioResponse;
import dds.grupo4.tpimpacto.services.UsuarioService;
import dds.grupo4.tpimpacto.utils.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrarUsuarioResponse> register(@RequestBody RegistrarUsuarioRequest request) {
        return ResponseEntityUtils.toResponseEntity(usuarioService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntityUtils.toResponseEntity(usuarioService.login(request));
    }
}
