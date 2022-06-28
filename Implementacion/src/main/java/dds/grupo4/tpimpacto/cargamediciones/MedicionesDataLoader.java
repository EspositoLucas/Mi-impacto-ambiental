package dds.grupo4.tpimpacto.cargamediciones;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MedicionesDataLoader {

    public List<RowMedicionActividad> loadData(File file) throws IOException {
        List<RowMedicionActividad> rowsMedicionActividad = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(file)) {
            Sheet sheet = wb.getSheet("Hoja1");
            Iterator<Row> rowIterator = sheet.rowIterator();

            // Las primeras dos filas son de los titulos, asi que las salteamos
            rowIterator.next();
            rowIterator.next();

            Row row = rowIterator.next();
            while (!row.getCell(0).getStringCellValue().isEmpty()) {
                RowMedicionActividad rowMedicionActividad = RowMedicionActividad.fromRow(row);
                rowsMedicionActividad.add(rowMedicionActividad);

                row = rowIterator.next();
            }
        }

        return rowsMedicionActividad;
    }

}
