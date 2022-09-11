package dds.grupo4.tpimpacto.cargamediciones;

import dds.grupo4.tpimpacto.services.ExcelService;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

@Getter
@Setter
public class RowMedicionActividad {
    private final String actividad;
    private final String tipoDeConsumo;
    private final String valor;
    private final String periodicidad;
    private final String periodoImputacion;

    private RowMedicionActividad(String actividad, String tipoDeConsumo, String valor, String periodicidad,
                                 String periodoImputacion) {
        this.actividad = actividad;
        this.tipoDeConsumo = tipoDeConsumo;
        this.valor = valor;
        this.periodicidad = periodicidad;
        this.periodoImputacion = periodoImputacion;
    }

    public static RowMedicionActividad fromRow(Row row) {
        String actividad = ExcelService.readCellAsString(row.getCell(0));
        String tipoDeConsumo = ExcelService.readCellAsString(row.getCell(1));
        String valor = ExcelService.readCellAsString(row.getCell(2));
        String periodicidad = ExcelService.readCellAsString(row.getCell(3));
        String periodoImputacion = ExcelService.readCellAsString(row.getCell(4));
        return new RowMedicionActividad(actividad, tipoDeConsumo, valor, periodicidad, periodoImputacion);
    }

    @Override
    public String toString() {
        return "RowMedicionActividad{" +
                "actividad='" + actividad + '\'' +
                ", tipoDeConsumo='" + tipoDeConsumo + '\'' +
                ", valor='" + valor + '\'' +
                ", periodicidad='" + periodicidad + '\'' +
                ", periodoImputacion='" + periodoImputacion + '\'' +
                '}';
    }
}
