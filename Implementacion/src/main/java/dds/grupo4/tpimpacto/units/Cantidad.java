package dds.grupo4.tpimpacto.units;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Cantidad {
    @ManyToOne
    @JoinColumn(name = "unidad", nullable = false)
    private Unidad unidad;

    private double valor;

    public Cantidad(Unidad unidad, double valor) {
        this.unidad = unidad;
        this.valor = valor;
    }

    public Cantidad toUnidadBase() {
        return new Cantidad(unidad.getTipoUnidad().getUnidadBase(), getValorEquivalenteEnUnidadBase());
    }

    public Cantidad toUnidad(Unidad nuevaUnidad) {
        if (this.unidad.equals(nuevaUnidad)) {
            return this;
        }

        assertUnidadesSonDelMismoTipo(this.unidad, nuevaUnidad);

        if (nuevaUnidad.isBase()) {
            return this.toUnidadBase();
        }

        if (this.unidad.isBase()) {
            return new Cantidad(nuevaUnidad, getValorEquivalenteDesdeBaseANuevaUnidad(this.valor, nuevaUnidad));
        }

        Cantidad thisEnUnidadBase = this.toUnidadBase();
        return thisEnUnidadBase.toUnidad(nuevaUnidad);
    }

    public Cantidad add(Cantidad other) {
        // Si las Cantidades son de la misma Unidad, las puedo sumar directamente
        if (this.unidad.equals(other.unidad)) {
            return new Cantidad(this.unidad, this.valor + other.valor);
        }

        assertUnidadesSonDelMismoTipo(this.unidad, other.unidad);

        Cantidad thisEnUnidadBase = this.toUnidadBase();
        Cantidad otherEnUnidadBase = other.toUnidadBase();
        Cantidad resultadoEnUnidadBase = thisEnUnidadBase.add(otherEnUnidadBase);
        return resultadoEnUnidadBase.toUnidad(this.unidad);
    }

    private void assertUnidadesSonDelMismoTipo(Unidad unidad1, Unidad unidad2) {
        if (!unidad1.getTipoUnidad().equals(unidad2.getTipoUnidad())) {
            throw new IllegalArgumentException(
                    "No se pueden sumar dos Cantidades cuyas unidades son de distintos tipos " +
                            "(LeftUnit = \"" + unidad1.getTipoUnidad().toString() + "\", " +
                            "RightUnit = \"" + unidad2.getTipoUnidad().toString() + "\")"
            );
        }
    }

    private double getValorEquivalenteEnUnidadBase() {
        return unidad.getFactorDeConversionAUnidadBase() * valor;
    }

    private double getValorEquivalenteDesdeBaseANuevaUnidad(double valorEnUnidadBase, Unidad nuevaUnidad) {
        return nuevaUnidad.getFactorDeConversionDesdeUnidadBase() * valorEnUnidadBase;
    }
}
