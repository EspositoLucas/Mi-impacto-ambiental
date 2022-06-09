package dds.grupo4.tpimpacto.services.apiSwagger.entities;

import java.util.List;
import java.util.Optional;

public class ListadoPaises {
    public List<Pais> paises;
    public Parametro parametros;

    public Optional<Pais> paisesDeId(int id){
        return this.paises.stream()
                .filter(p -> p.idPais == id)
                .findFirst();
    }
    private class Parametro {
        public int offset;

    }
}
