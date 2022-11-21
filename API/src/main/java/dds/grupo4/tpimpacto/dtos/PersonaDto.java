package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto extends BaseEntityDto {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String documento;

    private PersonaDto(Persona persona) {
        super(persona);
    }

    public static PersonaDto from(Persona persona) {
        PersonaDto dto = new PersonaDto(persona);
        dto.setNombre(persona.getNombre());
        dto.setApellido(persona.getApellido());
        dto.setTipoDocumento(persona.getTipoDocumento().toString());
        dto.setDocumento(persona.getDocumento());
        return dto;
    }
}
