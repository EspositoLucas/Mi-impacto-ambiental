package dominio;

public class Direccion {

    private String calle ;
    private Integer altura ;
    private String barrio ;
    private Integer codigoPostal;

    public Direccion(String calle, Integer altura, String barrio, Integer codigoPostal) {
        this.calle = calle;
        this.altura = altura;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;
    }
}
