package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.TipoDocumento;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Persona")
@Table(name = "personas")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Persona extends BaseEntity {

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String documento;

    @OneToMany(mappedBy = "persona")
    private List<Miembro> miembros = new ArrayList<>();

    public Persona(String nombre, String apellido, TipoDocumento tipoDocumento, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.setPersona(this);
    }

}
