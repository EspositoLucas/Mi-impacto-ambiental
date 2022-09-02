package dds.grupo4.tpimpacto.units;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Cantidad {
    @ManyToOne
    @JoinColumn(name = "unidad")
    private Unidad unidad;

    private double valor;

    public Cantidad(Unidad unidad, double valor) {
        this.unidad = unidad;
        this.valor = valor;
    }

    public Cantidad toUnidadBase() {
        if (unidad == null) {
            return this;
        }
        return new Cantidad(unidad.getTipoUnidad().getUnidadBase(), getValorEquivalenteEnUnidadBase());
    }

    public Cantidad toUnidad(Unidad nuevaUnidad) {
        if (Objects.equals(this.unidad, nuevaUnidad)) {
            return this;
        }

        if (this.unidad == null) {
            throw new IllegalArgumentException("No se puede convertir una Cantidad sin unidades a " + nuevaUnidad);
        }

        if (nuevaUnidad == null) {
            throw new IllegalArgumentException("No se puede convertir una Cantidad en " + this.unidad + " a otra sin unidades");
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
        if (Objects.equals(this.unidad, other.unidad)) {
            return new Cantidad(this.unidad, this.valor + other.valor);
        }

        assertAmbasCantidadesSonConOSinUnidades(this, other);
        assertUnidadesSonDelMismoTipo(this.unidad, other.unidad);

        Cantidad thisEnUnidadBase = this.toUnidadBase();
        Cantidad otherEnUnidadBase = other.toUnidadBase();
        Cantidad resultadoEnUnidadBase = thisEnUnidadBase.add(otherEnUnidadBase);
        return resultadoEnUnidadBase.toUnidad(this.unidad);
    }

    public boolean tieneUnidad() {
        return unidad != null;
    }

    private void assertAmbasCantidadesSonConOSinUnidades(Cantidad cantidad1, Cantidad cantidad2) {
        if (cantidad1.tieneUnidad() != cantidad2.tieneUnidad()) {
            throw new IllegalArgumentException(
                    "Cantidades con y sin unidades son incompatibles" +
                            "(LeftUnit = \"" + cantidad1.getUnidadAsStringOSinUnidad() + "\", " +
                            "RightUnit = \"" + cantidad2.getUnidadAsStringOSinUnidad() + "\")"
            );
        }
    }

    private void assertUnidadesSonDelMismoTipo(Unidad unidad1, Unidad unidad2) {
        if (unidad1 == null || unidad2 == null) {
            return;
        }
        if (!Objects.equals(unidad1.getTipoUnidad(), unidad2.getTipoUnidad())) {
            throw new IllegalArgumentException(
                    "Cantidades con unidades de distintos tipos " +
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

    private String getUnidadAsStringOSinUnidad() {
        if (unidad == null) {
            return "sin unidad";
        }
        return unidad.toString();
    }
}
