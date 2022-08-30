package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarUsuarioRequest {
    private String username;
    private String password;
    private Long idMiembro; // Si es distinto de NULL, entonces vincula al Usuario creado con el Miembro
}
