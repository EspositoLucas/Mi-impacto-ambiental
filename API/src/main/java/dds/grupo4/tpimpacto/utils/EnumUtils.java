package dds.grupo4.tpimpacto.utils;

import dds.grupo4.tpimpacto.dtos.IdTextPair;

public class EnumUtils {

    public static <E extends Enum<E>> IdTextPair enumToIdTextPair(E _enum) {
        return new IdTextPair(_enum.ordinal(), _enum.toString());
    }

}
