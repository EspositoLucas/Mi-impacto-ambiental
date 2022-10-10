package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos;

import dds.grupo4.tpimpacto.entities.geo.Localidad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadDto extends BaseGeoApiDto {
    private String codPostal;

    public Localidad toLocalidad() {
        return new Localidad(id, nombre, codPostal);
    }
}
