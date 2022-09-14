package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos;

import dds.grupo4.tpimpacto.entities.geo.Provincia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinciaDto extends BaseGeoApiDto {
    public Provincia toProvincia() {
        return new Provincia(id, nombre);
    }
}
