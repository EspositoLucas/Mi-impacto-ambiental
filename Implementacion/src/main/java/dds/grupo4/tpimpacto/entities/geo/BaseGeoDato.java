package dds.grupo4.tpimpacto.entities.geo;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseGeoDato extends BaseEntity {
    private int idSegunApi; // El id que devuelve la API de geodds
    private String nombre;

    public BaseGeoDato(int idSegunApi, String nombre) {
        this.idSegunApi = idSegunApi;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
