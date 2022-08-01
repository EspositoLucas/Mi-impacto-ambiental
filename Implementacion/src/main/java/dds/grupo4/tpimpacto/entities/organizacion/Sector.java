package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Sector")
@Table(name = "sectores")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sector extends BaseEntity {

    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizacion", nullable = false, foreignKey = @ForeignKey(name = "FK_Sectores_Organizacion"))
    private Organizacion organizacion;

    /*
     * TODO: ver si esto es un OneToOne o un ManyToOne.
     * Si en un mismo Espacio pueden haber distintos Sectores, seria ManyToOne.
     * Si en cada Espacio solamente puede haber un Sector, seria un OneToOne.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "espacio",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Sectores_Espacio")
    )
    private Espacio espacio;

    @OneToMany(mappedBy = "sector")
    private List<Miembro> miembros = new ArrayList<>();

    public Sector(String nombre, Organizacion organizacion, Espacio espacio) {
        this.nombre = nombre;
        this.organizacion = organizacion;
        this.espacio = espacio;
    }

    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.setSector(this);
    }

}
