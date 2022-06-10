package dds.grupo4.tpimpacto.services.apiSwagger.entities;

import java.util.List;
import java.util.Optional;

public class ListadoMunicipios {

    public Provincia provincia;
    public List<Municipio> municipios;
    public ParametroMunicipio parametros;

    public Optional<Municipio> municipiosDeId(int id) {
        return this.municipios.stream()
                .filter(p -> p.idMunicipio == id)
                .findFirst();
    }

    private class ParametroMunicipio {
        public int offset;
        public int provinciaId;
    }

}
