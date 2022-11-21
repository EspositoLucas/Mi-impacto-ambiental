package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearMiembroRequest {
    private IdTextPair persona;
    private IdTextPair organizacion;
    private IdTextPair sector;
}
