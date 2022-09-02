package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Unidad")
@Table(name = "unidades")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Unidad extends BaseEntity {

    private String simbolo;
    private String nombre;

    @ManyToOne
    @JoinColumn(
            name = "tipo_unidad",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Unidades_TipoUnidad")
    )
    private TipoUnidad tipoUnidad;

    @Column(name = "es_base")
    private boolean base; // Si es la unidad base del TipoUnidad que le corresponde

    private double factorDeConversionAUnidadBase;

    public Unidad(String simbolo, String nombre, boolean base, double factorDeConversionAUnidadBase) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.base = base;
        this.factorDeConversionAUnidadBase = factorDeConversionAUnidadBase;
    }

    public double getFactorDeConversionDesdeUnidadBase() {
        return 1 / factorDeConversionAUnidadBase;
    }
}
