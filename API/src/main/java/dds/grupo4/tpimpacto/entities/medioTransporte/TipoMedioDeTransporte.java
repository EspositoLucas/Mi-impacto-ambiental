package dds.grupo4.tpimpacto.entities.medioTransporte;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TipoMedioDeTransporte")
@Table(name = "tipos_medio_de_transporte")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TipoMedioDeTransporte extends BaseEntity {

    private String nombre;

    @OneToOne(mappedBy = "tipoMedioDeTransporte", cascade = CascadeType.ALL)
    private FactorDeEmision factorDeEmision;

    @OneToMany(mappedBy = "tipoMedioDeTransporte", cascade = CascadeType.ALL)
    private List<MedioDeTransporte> mediosDeTransporte = new ArrayList<>();

    public TipoMedioDeTransporte(String nombre) {
        this.nombre = nombre;
    }

    public void setFactorDeEmision(FactorDeEmision factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
        factorDeEmision.setTipoMedioDeTransporte(this);
    }

    public void addMedioDeTransporte(MedioDeTransporte medioDeTransporte) {
        mediosDeTransporte.add(medioDeTransporte);
        medioDeTransporte.setTipoMedioDeTransporte(this);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
