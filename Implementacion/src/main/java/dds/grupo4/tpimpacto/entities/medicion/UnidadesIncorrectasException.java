package dds.grupo4.tpimpacto.entities.medicion;

public class UnidadesIncorrectasException extends Throwable {
    public UnidadesIncorrectasException(String mensaje) {
        super(mensaje);
    }

    public UnidadesIncorrectasException() {
        super("La unidad ingresada no concuerda con la esperada.");
    }
}
