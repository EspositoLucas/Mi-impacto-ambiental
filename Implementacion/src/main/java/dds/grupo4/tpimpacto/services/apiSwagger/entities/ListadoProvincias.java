package dds.grupo4.tpimpacto.services.apiSwagger.entities;

import java.util.List;
import java.util.Optional;

public class ListadoProvincias {

    public Pais pais;
    public List<Provincia> provincias;

    public ParametroProvincia parametros;

    public Optional<Provincia> provinciaDeId(int id) {
        return this.provincias.stream()
                .filter(p -> p.idProvincia == id)
                .findFirst();
    }

    private class ParametroProvincia {
        public int offset;
        public int paisId;
    }

}
