package dds.grupo4.tpimpacto.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
public class ExcelService {
    public <T> List<T> loadData(InputStream inputStream, String sheetName, int titleRowsQty, Function<Row, T> rowMapper) throws IOException {
        List<T> mappedRows = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(inputStream)) {
            Sheet sheet = wb.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.rowIterator();

            // Me salteo las filas que corresponden a las cabeceras del Excel
            for (int i = 1; i <= titleRowsQty; i++) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                T mappedRow = rowMapper.apply(row);
                mappedRows.add(mappedRow);
                log.debug("Row leida: " + mappedRow.toString());
            }
        }

        return mappedRows;
    }

    public static boolean cellIsEmpty(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK)
            return true;

        if (cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue().trim();
            if (cellValue.isEmpty() || cellValue.equals("-"))
                return true;
        }

        return false;
    }

    public static String getCellStringValueOrNullIfEmpty(Cell cell) {
        if (cellIsEmpty(cell))
            return null;
        return cell.getStringCellValue().trim();
    }

    public static String readCellAsString(Cell cell) {
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }
}
