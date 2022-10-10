package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TipoUnidad")
@Table(name = "tipos_de_unidad")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoUnidad extends BaseEntity {

    private String nombre;

    @OneToMany(mappedBy = "tipoUnidad", cascade = CascadeType.ALL)
    private List<Unidad> unidades = new ArrayList<>();

    public TipoUnidad(String nombre) {
        this.nombre = nombre;
    }

    public void addUnidad(Unidad unidad) {
        unidades.add(unidad);
        unidad.setTipoUnidad(this);
    }

    public void addUnidades(List<Unidad> unidades) {
        unidades.forEach(this::addUnidad);
    }

    public Unidad getUnidadBase() {
        return unidades.stream()
                .filter(Unidad::isBase)
                .findFirst()
                .orElse(null);
    }
}
