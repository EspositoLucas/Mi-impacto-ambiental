package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos;

import dds.grupo4.tpimpacto.entities.geo.Municipio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioDto extends BaseGeoApiDto {
    public Municipio toMunicipio() {
        return new Municipio(id, nombre);
    }
}
