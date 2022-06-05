package dds.grupo4.tpimpacto.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Miembro")
@Table(name = "miembros")
public class Miembro extends BaseEntity {

    private Persona persona;
    private Usuario usuario;
    private Sector sector;
    private List<Tramo> tramos = new ArrayList<>();

    private List<Organizacion> organizaciones = new ArrayList<>(); //  para saber las organizaciones a las que pertenece un mimebro

    // Hibernate
    protected Miembro() {
    }

<<<<<<< HEAD
    public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento, List<Sector> sectores, List<Trayecto> trayectos, List<Organizacion> organizaciones) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.sectores = sectores;
        this.trayectos = trayectos;
        this.organizaciones = organizaciones;
    }

    public void setOrganizaciones(List<Organizacion> organizaciones) {
        this.organizaciones = organizaciones;
=======
    public Miembro(Persona persona, Sector sector) {
        this.persona = persona;
        this.sector = sector;
>>>>>>> e1d416f4eef3b15244fab6944571afefdf3d9703
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    public Organizacion getOrganizacion() {
        return this.sector.getOrganizacion();
    }

<<<<<<< HEAD
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
    public void agregarTrayecto(Trayecto trayecto) {
        this.trayectos.add(trayecto);
    }



    // Metodo para obtener las organizaciones si se trata de buscar una o mas organizaciones en específico

    public List<Organizacion> getOrganizaciones() {
        return this.sectores.stream()
                .map(s -> s.getOrganizacion())
                .distinct()
                .collect(Collectors.toList());
=======
    public void addTramo(Tramo tramo) {
        tramos.add(tramo);
>>>>>>> e1d416f4eef3b15244fab6944571afefdf3d9703
    }

    // calculo para la huella de carbono

//    public double calculoHC() {
//        return ;
//    }

}
