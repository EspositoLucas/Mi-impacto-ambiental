package dds.grupo4.tpimpacto.controllers;

import com.sun.istack.NotNull;
import dds.grupo4.tpimpacto.common.LectorDeArchivoImpl;
import dds.grupo4.tpimpacto.common.ValidadorContrasenia;
import dds.grupo4.tpimpacto.entities.seguridad.Administrador;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;

import static java.util.Objects.requireNonNull;

public class AdministradorContollerFake {

    private String usuario = null;
    private String contrasenia = null;

    public AdministradorContollerFake definirUsuario(@NotNull String usuario) {
        this.usuario = usuario;
        return this;
    }

    public AdministradorContollerFake definirContrasenia(@NotNull String contrasenia) {
        ValidadorContrasenia validador =new ValidadorContrasenia(new LectorDeArchivoImpl());
        validador.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
        return this;
    }

    public Administrador crearAdmin() {
        return new Administrador(
                requireNonNull(usuario, "Usuario no deberia ser nulo"),
                requireNonNull(contrasenia, "contrasenia no deberia ser nulo")
        );
    }
}
