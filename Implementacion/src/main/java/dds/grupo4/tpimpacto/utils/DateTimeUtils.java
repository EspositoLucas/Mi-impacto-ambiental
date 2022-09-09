package dds.grupo4.tpimpacto.utils;

import java.time.LocalDate;

public class DateTimeUtils {

    public static LocalDate getDateFromString(String str) {
        int day = 1, month = 1, year;
        String[] parts = str.split("/");
        if (parts.length == 3) {
            // dd/MM/yyyy
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } else if (parts.length == 2) {
            // MM/yyyy
            month = Integer.parseInt(parts[0]);
            year = Integer.parseInt(parts[1]);
        } else {
            // yyyy
            year = Integer.parseInt(parts[0]);
        }
        return LocalDate.of(year, month, day);
    }

}
