package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.dtos.LoginRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioRequest;
import dds.grupo4.tpimpacto.dtos.RegistrarUsuarioResponse;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrarUsuarioResponse> register(@RequestBody RegistrarUsuarioRequest request) {
        return usuarioService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest request) {
        return usuarioService.login(request);
    }
}
