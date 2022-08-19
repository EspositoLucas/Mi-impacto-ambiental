package dds.grupo4.tpimpacto.entities.seguridad;

import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import dds.grupo4.tpimpacto.entities.medicion.UnidadFactorEmision;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.repositories.TipoConsumoRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrador extends Usuario{


    public Administrador(String username, String password) {
        super(username, password);
    }

    public void cargarFactorDeEmision(Double valor, UnidadFactorEmision unidad) {
               new FactorDeEmision(valor, unidad);
    }
}
