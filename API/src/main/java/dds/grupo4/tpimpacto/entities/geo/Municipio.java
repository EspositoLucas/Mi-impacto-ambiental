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

@Entity(name = "Municipio")
@Table(name = "municipios")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Municipio extends BaseGeoDato {

    @OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL)
    private List<Localidad> localidades = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "provincia", nullable = false, foreignKey = @ForeignKey(name = "FK_Municipios_Provincia"))
    private Provincia provincia;

    public Municipio(int idSegunApi, String nombre) {
        super(idSegunApi, nombre);
    }

    public void addLocalidad(Localidad localidad) {
        localidades.add(localidad);
        localidad.setMunicipio(this);
    }

}
