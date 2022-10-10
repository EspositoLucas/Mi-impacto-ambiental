package dds.grupo4.tpimpacto.dtos.base;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntityDto {
    protected Long id;

    public BaseEntityDto(BaseEntity entity) {
        this.id = entity.getId();
    }
}
