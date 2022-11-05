package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Contacto")
@Table(name = "contactos")
@Getter
@Setter
@NoArgsConstructor
public class Contacto extends BaseEntity {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Boolean deseaRecibirPorWhatsapp;
    private Boolean deseaRecibirPorMail;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false, foreignKey = @ForeignKey(name = "FK_Contactos_Organizacion"))
    private Organizacion organizacion;

    public Contacto(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        String mail = email != null ? email : "-";
        String telef = telefono != null ? telefono : "-";
        return nombre + " " + apellido + " (Mail: " + mail + ", Telefono: " + telef + ")";
    }
}
