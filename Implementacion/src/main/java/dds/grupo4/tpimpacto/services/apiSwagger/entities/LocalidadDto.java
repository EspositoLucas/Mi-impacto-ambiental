package dds.grupo4.tpimpacto.services.apiSwagger.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadDto extends BaseGeoApiDto {

    private String nombre;
    private int codPostal;

}
