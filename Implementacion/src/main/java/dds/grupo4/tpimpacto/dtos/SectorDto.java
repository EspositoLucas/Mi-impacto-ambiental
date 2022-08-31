package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorDto {
    private long id;
    private String nombre;
    private IdTextPair organizacion;
    private IdTextPair espacio;

    public static SectorDto from(Sector sector) {
        SectorDto dto = new SectorDto();
        dto.setId(sector.getId());
        dto.setNombre(sector.getNombre());
        dto.setOrganizacion(new IdTextPair(sector.getOrganizacion()));
        dto.setEspacio(new IdTextPair(sector.getEspacio()));
        return dto;
    }
}
