package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoDocumento;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Miembro")
@Table(name = "miembros")

public class Miembro extends BaseEntity {

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private List<Sector> sectores = new ArrayList<>();
    private List<Trayecto> trayectos = new ArrayList<>();

    // Hibernate
    protected Miembro() {
    }

    public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
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

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }


    // Metodo para agregar trayecto
    public void agregarTrayecto(Trayecto trayecto)
    {
        this.trayectos.add(trayecto);
    }

    // Metodo para obtener las organizaciones
    public List<Organizacion> getOrganizaciones() {
        return this.sectores.stream()
                .map(s -> s.getOrganizacion())
                .distinct()
                .collect(Collectors.toList());
    }

    // calculo para la huella de carbono

//    public double calculoHC() {
//        return ;
//    }

}
