package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;

import java.util.List;

public interface OrganizacionService {
    void cargarMediciones(List<RowMedicionActividad> mediciones);
}
