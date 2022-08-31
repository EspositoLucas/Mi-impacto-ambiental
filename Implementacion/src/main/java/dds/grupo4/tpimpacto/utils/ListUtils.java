package dds.grupo4.tpimpacto.utils;

import dds.grupo4.tpimpacto.dtos.IdTextPair;
import dds.grupo4.tpimpacto.entities.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    public static <T> T getLast(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T extends BaseEntity> List<IdTextPair> toIdTextPairList(List<T> entities) {
        return entities.stream()
                .map(IdTextPair::new)
                .collect(Collectors.toList());
    }

}
