package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.Organizacion;

import java.util.List;

public interface OrganizacionService {
    void save(Organizacion organizacion);

    List<Organizacion> getAll();

    void cargarMediciones(List<RowMedicionActividad> mediciones);
}
