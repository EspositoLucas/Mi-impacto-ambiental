package dds.grupo4.tpimpacto.entities.medioTransporte;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TransportePublico")
@Table(name = "transportes_publicos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransportePublico extends MedioDeTransporte {

    private TipoTransportePublico tipoTransportePublico;
    private String linea;

    @OneToMany(mappedBy = "transportePublico")
    private List<Parada> paradas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "combustible",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_TransportesPublicos_Combustible")
    )
    private Combustible combustible;

    private double combustibleConsumidoPorKm;

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Parada> paradas, Combustible combustible, double combustibleConsumidoPorKm) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.linea = linea;
        this.paradas = paradas;
        this.combustible = combustible;
        this.combustibleConsumidoPorKm = combustibleConsumidoPorKm;
    }

    public void addParada(Parada parada) {
        paradas.add(parada);
        parada.setTransportePublico(this);
    }

}
