package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IdTextPair {
    private long id;
    private String text;

    public IdTextPair(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public IdTextPair(BaseEntity entity) {
        this(entity.getId(), entity.toString());
    }
}
