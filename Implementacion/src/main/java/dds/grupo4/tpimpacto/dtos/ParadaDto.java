package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParadaDto {
    private long id;
    private IdTextPair transportePublico;
    private DireccionDto direccion;
    private Double distanciaParadaSiguiente;
    private long idParadaSiguiente;

    public static ParadaDto from(Parada parada) {
        ParadaDto dto = new ParadaDto();
        dto.setId(parada.getId());
        dto.setTransportePublico(new IdTextPair(parada.getTransportePublico()));
        dto.setDireccion(DireccionDto.from(parada.getDireccion()));
        dto.setDistanciaParadaSiguiente(parada.getDistanciaParadaSiguiente());
        if (parada.getParadaSiguiente() != null) {
            dto.setIdParadaSiguiente(parada.getParadaSiguiente().getId());
        }
        return dto;
    }

}
