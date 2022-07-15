package dds.grupo4.tpimpacto.entities.organizacion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Sector")
@Table(name = "sectores")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sector extends BaseEntity {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false)
    private Organizacion organizacion;

    /*
     * TODO: ver si esto es un OneToOne o un ManyToOne.
     * Si en un mismo Espacio pueden haber distintos Sectores (que calculo que no),
     * seria ManyToOne.
     * Si en cada Espacio solamente puede haber un Sector, seria un OneToOne.
     */
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

    // calculo HC promedio

//    public double calculoHCPromedioMensual() {
//        return organizacion.calculoHCTotalMensual() / this.miembros.size();
//    }
//    public double calculoHCPromedioAnual() {
//        return organizacion.calculoHCtotalAnual() / this.miembros.size();
//    }

}
