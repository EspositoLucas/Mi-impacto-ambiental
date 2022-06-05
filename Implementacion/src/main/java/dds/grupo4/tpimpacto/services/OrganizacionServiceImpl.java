package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;

import java.util.List;

public class OrganizacionServiceImpl implements OrganizacionService {

    @Override
    public void cargarMediciones(List<RowMedicionActividad> mediciones) {
        for (RowMedicionActividad rowMedicionActividad : mediciones) {
            System.out.println(rowMedicionActividad.toString());
        }
    }

}
