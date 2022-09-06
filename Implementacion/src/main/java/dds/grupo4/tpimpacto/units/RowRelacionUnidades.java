package dds.grupo4.tpimpacto.units;

import dds.grupo4.tpimpacto.services.ExcelService;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

@Getter
@Setter
public class RowRelacionUnidades {
    private final String unidadIzquierda;
    private final String unidadDerecha;
    private final String unidadProducto;
    private final String unidadCociente;

    private RowRelacionUnidades(String unidadIzquierda, String unidadDerecha, String unidadProducto, String unidadCociente) {
        this.unidadIzquierda = unidadIzquierda;
        this.unidadDerecha = unidadDerecha;
        this.unidadProducto = unidadProducto;
        this.unidadCociente = unidadCociente;
    }

    public static RowRelacionUnidades fromRow(Row row) {
        String unidadIzquierda = ExcelService.getCellStringValueOrNullIfEmpty(row.getCell(0));
        String unidadDerecha = ExcelService.getCellStringValueOrNullIfEmpty(row.getCell(1));
        String unidadProducto = ExcelService.getCellStringValueOrNullIfEmpty(row.getCell(2));
        String unidadCociente = ExcelService.getCellStringValueOrNullIfEmpty(row.getCell(3));
        return new RowRelacionUnidades(unidadIzquierda, unidadDerecha, unidadProducto, unidadCociente);
    }

    @Override
    public String toString() {
        return "RowRelacionUnidades{" +
                "unidadIzquierda='" + unidadIzquierda + '\'' +
                ", unidadDerecha='" + unidadDerecha + '\'' +
                ", unidadProducto='" + unidadProducto + '\'' +
                ", unidadCociente='" + unidadCociente + '\'' +
                '}';
    }
}
