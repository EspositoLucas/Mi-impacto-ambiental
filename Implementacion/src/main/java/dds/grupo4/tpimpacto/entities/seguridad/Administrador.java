package dds.grupo4.tpimpacto.entities.seguridad;

import dds.grupo4.tpimpacto.entities.medicion.FactorEmision;
import dds.grupo4.tpimpacto.entities.medicion.UnidadFactorEmision;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Administrador extends Usuario {

    public Administrador(String username, String password) {
        super(username, password);
    }

    public void setFactorEmision(Double valorEmision, UnidadFactorEmision unidadEmision) {
        new FactorEmision(valorEmision, unidadEmision);
    }
}
