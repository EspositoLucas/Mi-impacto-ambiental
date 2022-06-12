package dds.grupo4.tpimpacto.services.apiSwagger.entities;

public class LocalidadDto extends BaseGeoApiDto {

    private String nombre;
    private int codPostal;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }
}
