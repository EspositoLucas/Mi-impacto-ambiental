package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDto {
    private Long id;
    private String calle;
    private String altura;
    private String pais;
    private String provincia;
    private String municipio;
    private String localidad;
    private String barrio;
    private int codigoPostal;

    public static DireccionDto from(Direccion direccion) {
        DireccionDto dto = new DireccionDto();
        dto.setId(direccion.getId());
        dto.setCalle(direccion.getCalle());
        dto.setAltura(direccion.getAltura());
        dto.setPais(direccion.getPais());
        dto.setProvincia(direccion.getProvincia());
        dto.setMunicipio(direccion.getMunicipio());
        dto.setLocalidad(direccion.getLocalidad());
        dto.setBarrio(direccion.getBarrio());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        return dto;
    }
}
