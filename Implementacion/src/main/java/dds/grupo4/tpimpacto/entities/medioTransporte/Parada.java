package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Parada")
@Table(name = "paradas")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parada extends Lugar {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "transporte_publico", nullable = false, foreignKey = @ForeignKey(name = "FK_Paradas_TransportePublico"))
    private TransportePublico transportePublico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "distancia_parada_siguiente", foreignKey = @ForeignKey(name = "FK_Paradas_DistanciaParadaSiguiente"))
    private Cantidad distanciaParadaSiguiente;

    @OneToOne
    @JoinColumn(
            name = "parada_siguiente",
            unique = true,
            foreignKey = @ForeignKey(name = "FK_Paradas_ParadaSiguiente")
    )
    private Parada paradaSiguiente;

    public Parada(Direccion direccion, Cantidad distanciaParadaSiguiente) {
        super(direccion);
        this.distanciaParadaSiguiente = distanciaParadaSiguiente;
    }

    @Override
    public String toString() {
        return "Parada de " + transportePublico.toString() + " en " + direccion.toString();
    }
}
