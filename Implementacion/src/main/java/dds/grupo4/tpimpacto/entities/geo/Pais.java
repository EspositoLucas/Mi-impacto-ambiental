package dds.grupo4.tpimpacto.entities.geo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pais")
@Table(name = "paises")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pais extends BaseGeoDato {

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Provincia> provincias = new ArrayList<>();

    public Pais(int idSegunApi, String nombre) {
        super(idSegunApi, nombre);
    }

    public void addProvincia(Provincia provincia) {
        provincias.add(provincia);
        provincia.setPais(this);
    }
}
