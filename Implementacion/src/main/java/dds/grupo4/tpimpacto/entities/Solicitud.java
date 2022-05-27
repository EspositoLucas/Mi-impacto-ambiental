package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Solicitud")
@Table(name = "solicitudes")
public class Solicitud extends BaseEntity {

    private Miembro miembro;
    private Sector sector;
    private Organizacion organizacion;

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

}
