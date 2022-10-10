package dds.grupo4.tpimpacto.entities.trayecto;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.geo.Localidad;
import dds.grupo4.tpimpacto.entities.geo.Municipio;
import dds.grupo4.tpimpacto.entities.geo.Pais;
import dds.grupo4.tpimpacto.entities.geo.Provincia;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity(name = "Direccion")
@Table(name = "direcciones")
@Immutable
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Direccion extends BaseEntity {

    private String calle;
    private String altura;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "localidad", nullable = false, foreignKey = @ForeignKey(name = "FK_Direcciones_Localidad"))
    private Localidad localidad;

    public Direccion(String calle, String altura) {
        this.calle = calle;
        this.altura = altura;
    }

    public Municipio getMunicipio() {
        return localidad.getMunicipio();
    }

    public Provincia getProvincia() {
        return getMunicipio().getProvincia();
    }

    public Pais getPais() {
        return getProvincia().getPais();
    }

    public String getCodigoPostal() {
        return localidad.getCodigoPostal();
    }

    @Override
    public String toString() {
        return calle + altura + ", " + localidad.toString();
    }
}
