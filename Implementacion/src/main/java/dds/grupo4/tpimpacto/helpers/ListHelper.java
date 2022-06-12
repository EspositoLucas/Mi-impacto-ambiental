package dds.grupo4.tpimpacto.helpers;

import java.util.List;

public class ListHelper {

    public static <T> T getLast(List<T> list) {
        return list.get(list.size() - 1);
    }

}
