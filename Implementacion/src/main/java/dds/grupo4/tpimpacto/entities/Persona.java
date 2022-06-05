package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoDocumento;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Persona")
@Table(name = "personas")
public class Persona extends BaseEntity {

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String documento;
    private List<Miembro> miembros = new ArrayList<>();

    // Hibernate
    protected Persona() {
    }

    public Persona(String nombre, String apellido, TipoDocumento tipoDocumento, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.setPersona(this);
    }

}
