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

    /**
     * @return True si <code>dateToTest</code> es menor o igual a <code>referenceDate</code>
     */
    public static boolean isBeforeOrEqual(LocalDate dateToTest, LocalDate referenceDate) {
        return (dateToTest.isBefore(referenceDate) || dateToTest.isEqual(referenceDate));
    }

    /**
     * @return True si <code>dateToTest</code> es mayor o igual a <code>referenceDate</code>
     */
    public static boolean isAfterOrEqual(LocalDate dateToTest, LocalDate referenceDate) {
        return (dateToTest.isAfter(referenceDate) || dateToTest.isEqual(referenceDate));
    }

    public static LocalDate dateWithOnlyYearAndMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

}
