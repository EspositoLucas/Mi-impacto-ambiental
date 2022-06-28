package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Contacto")
@Table(name = "contactos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contacto extends BaseEntity {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public Contacto(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

}
