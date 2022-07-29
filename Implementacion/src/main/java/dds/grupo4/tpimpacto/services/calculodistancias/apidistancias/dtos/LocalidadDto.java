package dds.grupo4.tpimpacto.services.calculodistancias.apidistancias.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadDto extends BaseGeoApiDto {

    private String nombre;
    private int codPostal;

}
