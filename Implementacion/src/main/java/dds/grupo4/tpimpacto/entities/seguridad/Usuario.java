package dds.grupo4.tpimpacto.entities.seguridad;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario extends BaseEntity {

    public static final int INTENTOS_DE_LOGEO_PERMITIDOS = 3; // Cuantas veces puede poner mal su password antes de que empiecen a correr los bloqueos

    private String username;
    private String password;
    private LocalDateTime bloqueadoHasta; // Hasta que fecha esta bloqueado
    private int cantidadIntentosIncorrectos = 0; // Cuantos logeos incorrectos seguidos hizo

    private Boolean esAdmin;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return username;
    }
}
