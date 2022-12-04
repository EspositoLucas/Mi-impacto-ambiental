package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.organizacion.Contacto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactoDto extends BaseEntityDto {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Boolean deseaRecibirPorWhatsapp;
    private Boolean deseaRecibirPorMail;

    private ContactoDto(Contacto contacto) {
        super(contacto);
    }

    public static ContactoDto from(Contacto contacto) {
        ContactoDto dto = new ContactoDto(contacto);
        dto.setNombre(contacto.getNombre());
        dto.setApellido(contacto.getApellido());
        dto.setEmail(contacto.getEmail());
        dto.setTelefono(contacto.getTelefono());
        dto.setDeseaRecibirPorWhatsapp(contacto.getDeseaRecibirPorWhatsapp());
        dto.setDeseaRecibirPorMail(contacto.getDeseaRecibirPorMail());
        return dto;
    }

}
