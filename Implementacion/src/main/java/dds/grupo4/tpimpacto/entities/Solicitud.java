package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Solicitud")
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Solicitud extends BaseEntity {

    private Miembro miembro;
    private Sector sector;
    private Organizacion organizacion;

    public Solicitud(Miembro miembro, Sector sector) {
        this.miembro = miembro;
        this.sector = sector;
    }

}
