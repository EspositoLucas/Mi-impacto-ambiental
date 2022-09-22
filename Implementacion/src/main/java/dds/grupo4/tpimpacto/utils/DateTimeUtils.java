package dds.grupo4.tpimpacto.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static String dateToString(LocalDate date, boolean includeDay) {
        DateTimeFormatter formatter = includeDay
                ? DateTimeFormatter.ofPattern("dd/MM/yyyy")
                : DateTimeFormatter.ofPattern("MM/yyyy");
        return date.format(formatter);
    }

    public static int mesesEntreFechas(LocalDate fechaInicioInclusiva, LocalDate fechaFinExclusiva) {
        // Ejemplo: entre el 01/2022 y el 02/2022 hay 1 mes
        // Ejemplo: entre el 12/2021 y el 01/2022 hay 1 mes
        int diffAnios = fechaFinExclusiva.getYear() - fechaInicioInclusiva.getYear();
        int diffMeses = fechaFinExclusiva.getMonthValue() - fechaInicioInclusiva.getMonthValue();
        return diffAnios * 12 + diffMeses;
    }

}
