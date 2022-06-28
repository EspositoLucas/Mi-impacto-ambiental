package dds.grupo4.tpimpacto.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Parada")
@Table(name = "paradas")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parada extends Lugar {

    private TransportePublico transportePublico;
    private Double distanciaProxima;
    private Double distanciaAnterior;
    private Parada paradaSiguiente;

    public Parada(Direccion direccion, Double distanciaProxima) {
        super(direccion);
        this.distanciaProxima = distanciaProxima;
    }

}
