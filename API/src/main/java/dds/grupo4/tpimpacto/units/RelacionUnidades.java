package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity(name = "RelacionUnidades")
@Table(name = "relaciones_unidades")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class RelacionUnidades extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "unidad_izquierda",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadIzquierda")
    )
    private Unidad unidadIzquierda;

    @ManyToOne
    @JoinColumn(
            name = "unidad_derecha",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadDerecha")
    )
    private Unidad unidadDerecha;

    @ManyToOne
    @JoinColumn(
            name = "unidad_producto",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadProducto")
    )
    private Unidad unidadProducto;

    @ManyToOne
    @JoinColumn(
            name = "unidad_cociente",
            foreignKey = @ForeignKey(name = "FK_RelacionesUnidades_UnidadCociente")
    )
    private Unidad unidadCociente;

    public RelacionUnidades(Unidad unidadIzquierda, Unidad unidadDerecha, Unidad unidadProducto, Unidad unidadCociente) {
        this.unidadIzquierda = unidadIzquierda;
        this.unidadDerecha = unidadDerecha;
        this.unidadProducto = unidadProducto;
        this.unidadCociente = unidadCociente;
    }
}
