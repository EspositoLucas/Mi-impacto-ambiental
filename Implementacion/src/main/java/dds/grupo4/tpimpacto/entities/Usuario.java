package dds.grupo4.tpimpacto.entities;

import java.time.LocalDateTime;

public class Usuario {

    private static final int INTENTOS_PERMITIDOS = 3; // Cuantas veces puede poner mal su password antes de que empiecen a correr los bloqueos

    private final String username;
    private final String password;
    private LocalDateTime bloqueadoHasta; // Hasta que fecha esta bloqueado
    private int cantidadIntentosIncorrectos = 0; // Cuantos logeos incorrectos seguidos hizo

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean validarContrasenia(String password) {
        return this.password.equals(password);
    }

    public void logeoIncorrecto() {
        cantidadIntentosIncorrectos++;
        if (cantidadIntentosIncorrectos >= INTENTOS_PERMITIDOS) {
            // Si se logeo incorrectamente mas veces de las permitidas, se empieza a bloquear por cierto tiempo
            int segundosDeBloqueo = (cantidadIntentosIncorrectos - INTENTOS_PERMITIDOS) * 10;

            bloqueadoHasta = LocalDateTime.now().plusSeconds(segundosDeBloqueo);
        }
    }

    public void logeoCorrecto() {
        // Si se logeo correctamente reseteo los campos, para que la proxima vez que se logea mal los campos empiecen desde cero
        cantidadIntentosIncorrectos = 0;
        bloqueadoHasta = null;
    }

    public boolean estaBloqueado() {
        return bloqueadoHasta != null && LocalDateTime.now().isBefore(bloqueadoHasta);
    }

    public LocalDateTime getBloqueadoHasta() {
        return bloqueadoHasta;
    }

}
