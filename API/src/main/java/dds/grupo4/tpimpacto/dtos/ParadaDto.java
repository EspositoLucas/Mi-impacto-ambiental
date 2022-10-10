package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseEntityDto;
import dds.grupo4.tpimpacto.entities.medioTransporte.Parada;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParadaDto extends BaseEntityDto {
    private IdTextPair transportePublico;
    private DireccionDto direccion;
    private CantidadDto distanciaParadaSiguiente;
    private long idParadaSiguiente;

    private ParadaDto(Parada parada) {
        super(parada);
    }

    public static ParadaDto from(Parada parada) {
        ParadaDto dto = new ParadaDto(parada);
        dto.setTransportePublico(new IdTextPair(parada.getTransportePublico()));
        dto.setDireccion(DireccionDto.from(parada.getDireccion()));
        dto.setDistanciaParadaSiguiente(CantidadDto.from(parada.getDistanciaParadaSiguiente()));
        if (parada.getParadaSiguiente() != null) {
            dto.setIdParadaSiguiente(parada.getParadaSiguiente().getId());
        }
        return dto;
    }

}
