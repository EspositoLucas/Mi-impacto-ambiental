package dds.grupo4.tpimpacto.services.apiSwagger.entities;

import java.util.List;
import java.util.Optional;

public class ListadoProvincias {

    public Pais pais ;
    public List<Provincia> provincias;

    public Parametro parametros;

    public Optional<Provincia> provinciaDeId(int id){
        return this.provincias.stream()
                .filter(p -> p.idProvincia == id)
                .findFirst();
    }

    private class Parametro {
        public int offset;
        public int paisId;

    }
}
