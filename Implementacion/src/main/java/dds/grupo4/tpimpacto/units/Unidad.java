package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "Unidad")
@Table(name = "unidades")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Unidad extends BaseEntity {

    private String simbolo;
    private String nombre;
    private double factorDeConversionAUnidadBase;

    @Column(name = "es_base")
    private boolean base; // Si es la unidad base del TipoUnidad que le corresponde

    @ManyToOne
    @JoinColumn(
            name = "tipo_unidad",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_Unidades_TipoUnidad")
    )
    private TipoUnidad tipoUnidad;

    @OneToMany(mappedBy = "unidadIzquierda", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesEnIzquierda = new ArrayList<>();

    @OneToMany(mappedBy = "unidadDerecha", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesEnDerecha = new ArrayList<>();

    @OneToMany(mappedBy = "unidadProducto", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesProducto = new ArrayList<>();

    @OneToMany(mappedBy = "unidadCociente", cascade = CascadeType.ALL)
    private List<RelacionUnidades> relacionesUnidadesCociente = new ArrayList<>();

    public Unidad(String simbolo, String nombre, boolean base, double factorDeConversionAUnidadBase) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.base = base;
        this.factorDeConversionAUnidadBase = factorDeConversionAUnidadBase;
    }

    public static String toString(Unidad unidad) {
        if (unidad == null)
            return "-";
        return unidad.toString();
    }

    public double getFactorDeConversionDesdeUnidadBase() {
        return 1 / factorDeConversionAUnidadBase;
    }

    public List<RelacionUnidades> getAllRelacionesUnidades() {
        return Stream.concat(
                relacionesUnidadesEnIzquierda.stream(),
                Stream.concat(
                        relacionesUnidadesEnDerecha.stream(),
                        Stream.concat(
                                relacionesUnidadesProducto.stream(),
                                relacionesUnidadesCociente.stream()
                        )
                )
        ).collect(Collectors.toList());
    }

    public void addRelacionEnIzquierda(RelacionUnidades relacionUnidades) {
        relacionesUnidadesEnIzquierda.add(relacionUnidades);
        relacionUnidades.setUnidadIzquierda(this);
    }

    public void addRelacionEnDerecha(RelacionUnidades relacionUnidades) {
        relacionesUnidadesEnDerecha.add(relacionUnidades);
        relacionUnidades.setUnidadDerecha(this);
    }

    public void addRelacionEnProducto(RelacionUnidades relacionUnidades) {
        relacionesUnidadesProducto.add(relacionUnidades);
        relacionUnidades.setUnidadProducto(this);
    }

    public void addRelacionEnCociente(RelacionUnidades relacionUnidades) {
        relacionesUnidadesCociente.add(relacionUnidades);
        relacionUnidades.setUnidadCociente(this);
    }

    @Override
    public String toString() {
        return simbolo;
    }
}
