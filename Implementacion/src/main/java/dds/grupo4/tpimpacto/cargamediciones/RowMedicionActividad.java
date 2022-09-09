package dds.grupo4.tpimpacto.cargamediciones;

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
        String actividad = row.getCell(0).getStringCellValue();
        String tipoDeConsumo = row.getCell(1).getStringCellValue();
        String valor = row.getCell(2).getStringCellValue();
        String periodicidad = row.getCell(3).getStringCellValue();
        String periodoImputacion = row.getCell(4).getStringCellValue();
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
