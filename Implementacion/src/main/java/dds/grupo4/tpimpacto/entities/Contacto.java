package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity(name = "MedioDeTransporte")
@Table(name = "medios_de_transporte")
@Inheritance(strategy = InheritanceType.JOINED)
public class Contacto extends BaseEntity {
    private String nombre ;
    private String apellido;
    private String email ;
    private Integer telefono ;

    protected Contacto() {
    }

    public Contacto(String nombre, String apellido, String email, Integer telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }


}
