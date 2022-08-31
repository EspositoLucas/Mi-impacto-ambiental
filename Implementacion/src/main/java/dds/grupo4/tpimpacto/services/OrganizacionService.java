package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.dtos.AceptarSolicitudRequest;
import dds.grupo4.tpimpacto.dtos.CrearOrganizacionRequest;
import dds.grupo4.tpimpacto.dtos.ListarMiembrosResponse;
import dds.grupo4.tpimpacto.dtos.ListarOrganizacionesResponse;
import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;

import java.util.List;
import java.util.Optional;

public interface OrganizacionService extends BaseService<Organizacion> {
    BaseResponse crearOrganizacion(CrearOrganizacionRequest request);

    ListarOrganizacionesResponse listarOrganizaciones();

    BaseResponse aceptarSolicitud(AceptarSolicitudRequest request);

    ListarMiembrosResponse listarMiembros(long idOrganizacion);

    Optional<Organizacion> getByRazonSocial(String razonSocial);

    void agregarSolicitud(Organizacion organizacion, Solicitud solicitud);

    void cargarMediciones(Organizacion organizacion, List<RowMedicionActividad> mediciones);

    List<String> getMailsDeContactos();
}
