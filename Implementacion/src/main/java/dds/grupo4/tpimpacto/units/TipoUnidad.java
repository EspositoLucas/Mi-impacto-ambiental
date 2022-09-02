package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TipoUnidad")
@Table(name = "tipos_de_unidad")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoUnidad extends BaseEntity {

    private String tipo;

    @OneToMany(mappedBy = "tipoUnidad", cascade = CascadeType.ALL)
    private List<Unidad> unidades = new ArrayList<>();

    public TipoUnidad(String tipo) {
        this.tipo = tipo;
    }

    public void addUnidad(Unidad unidad) {
        unidades.add(unidad);
        unidad.setTipoUnidad(this);
    }

    public Unidad getUnidadBase() {
        return unidades.stream()
                .filter(Unidad::isBase)
                .findFirst()
                .orElse(null);
    }
}
