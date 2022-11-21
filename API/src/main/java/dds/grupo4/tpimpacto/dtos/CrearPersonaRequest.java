package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearPersonaRequest {
    private String nombre;
    private String apellido;
    private IdTextPair tipoDocumento;
    private String documento;
}
