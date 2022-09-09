package dds.grupo4.tpimpacto.entities.medicion;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.units.Cantidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "RegistroCalculoHCDatoActividad")
@Table(name = "registros_calculo_hc_dato_actividad")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistroCalculoHCDatoActividad extends BaseEntity {

    private Periodicidad periodicidad;
    private LocalDate periodoImputacion;

    @ManyToOne
    @JoinColumn(name = "organizacion", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCDatoActividad_Organizacion"))
    private Organizacion organizacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor", nullable = false,
            foreignKey = @ForeignKey(name = "FK_RegistrosCalculoHCDatoActividad_Valor"))
    private Cantidad valor;

    public RegistroCalculoHCDatoActividad(Periodicidad periodicidad, LocalDate periodoImputacion, Cantidad valor) {
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
        this.valor = valor;
    }
}
