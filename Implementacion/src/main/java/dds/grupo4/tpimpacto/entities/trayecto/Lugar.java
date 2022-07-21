package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Lugar")
@Table(name = "lugares")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Lugar extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "direccion", nullable = false, foreignKey = @ForeignKey(name = "FK_Lugares_Direccion"))
    private Direccion direccion;

    public Lugar(Direccion direccion) {
        this.direccion = direccion;
    }

}
