package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DireccionDto extends BaseEntityDto {
    private String calle;
    private String altura;
    private IdTextPair pais;
    private IdTextPair provincia;
    private IdTextPair municipio;
    private IdTextPair localidad;
    private String codigoPostal;

    private DireccionDto(Direccion direccion) {
        super(direccion);
    }

    public static DireccionDto from(Direccion direccion) {
        DireccionDto dto = new DireccionDto(direccion);
        dto.setCalle(direccion.getCalle());
        dto.setAltura(direccion.getAltura());
        dto.setPais(new IdTextPair(direccion.getPais()));
        dto.setProvincia(new IdTextPair(direccion.getProvincia()));
        dto.setMunicipio(new IdTextPair(direccion.getMunicipio()));
        dto.setLocalidad(new IdTextPair(direccion.getLocalidad()));
        dto.setCodigoPostal(direccion.getCodigoPostal());
        return dto;
    }
}
