package dds.grupo4.tpimpacto.services.apiSwagger.entities;

import java.util.List;
import java.util.Optional;

public class ListadoLocalidades {
    public Municipio municipio;
    public List<Localidad> localidades;
    public Parametro parametros;

    public Optional<Localidad> localidadesDeId(int id){
        return this.localidades.stream()
                .filter(p -> p.idLocalidad == id)
                .findFirst();
    }

    private class Parametro {
        public int offset;
        public int municipioId;

    }
}
