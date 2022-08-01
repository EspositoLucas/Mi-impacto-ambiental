package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.cargamediciones.RowMedicionActividad;
import dds.grupo4.tpimpacto.dtos.AceptarSolicitud;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Solicitud;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrganizacionService extends BaseService<Organizacion> {
    Optional<Organizacion> getByRazonSocial(String razonSocial);

    @Transactional
    AceptarSolicitud.Response aceptarSolicitud(AceptarSolicitud.Request request);

    void agregarSolicitud(Organizacion organizacion, Solicitud solicitud);

    void cargarMediciones(Organizacion organizacion, List<RowMedicionActividad> mediciones);

    List<String> getMailsDeContactos();
}
