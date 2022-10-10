package dds.grupo4.tpimpacto.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearTransportePublicoRequest {
    private String linea;
    private IdTextPair tipoMedioDeTransporte;
    private long idCombustible;
    private double combustibleConsumidoPorKm;
    // FactorDeEmision???
    private List<ParadaDto> paradas = new ArrayList<>();
}
