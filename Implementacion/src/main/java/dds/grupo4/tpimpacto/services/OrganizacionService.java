package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.entities.Organizacion;
import dds.grupo4.tpimpacto.entities.Solicitud;

import java.util.List;
import java.util.Optional;

public interface OrganizacionService extends BaseService<Organizacion> {
    Optional<Organizacion> getByRazonSocial(String razonSocial);

    void agregarSolicitud(Organizacion organizacion, Solicitud solicitud);

    void cargarMediciones(Organizacion organizacion, List<RowMedicionActividad> mediciones);
}
