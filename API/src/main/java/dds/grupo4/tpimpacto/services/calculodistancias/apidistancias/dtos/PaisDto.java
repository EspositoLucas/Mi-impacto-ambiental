package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos;

import dds.grupo4.tpimpacto.entities.geo.Pais;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisDto extends BaseGeoApiDto {
    public Pais toPais() {
        return new Pais(id, nombre);
    }
}
