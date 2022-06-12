package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    public static final int INTENTOS_DE_LOGEO_PERMITIDOS = 3; // Cuantas veces puede poner mal su password antes de que empiecen a correr los bloqueos

    private String username;
    private String password;
    private LocalDateTime bloqueadoHasta; // Hasta que fecha esta bloqueado
    private int cantidadIntentosIncorrectos = 0; // Cuantos logeos incorrectos seguidos hizo

    // Hibernate
    protected Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCantidadIntentosIncorrectos() {
        return cantidadIntentosIncorrectos;
    }

    public void setCantidadIntentosIncorrectos(int cantidadIntentosIncorrectos) {
        this.cantidadIntentosIncorrectos = cantidadIntentosIncorrectos;
    }

    public LocalDateTime getBloqueadoHasta() {
        return bloqueadoHasta;
    }

    public void setBloqueadoHasta(LocalDateTime bloqueadoHasta) {
        this.bloqueadoHasta = bloqueadoHasta;
    }

    public boolean validarContrasenia(String password) {
        return this.password.equals(password);
    }

    public void logeoIncorrecto() {
        cantidadIntentosIncorrectos++;
        if (cantidadIntentosIncorrectos >= INTENTOS_DE_LOGEO_PERMITIDOS) {
            // Si se logeo incorrectamente mas veces de las permitidas, se empieza a bloquear por cierto tiempo
            int intentosExcedidos = cantidadIntentosIncorrectos - INTENTOS_DE_LOGEO_PERMITIDOS;
            int segundosDeBloqueo = intentosExcedidos * 10;

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

}
