package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import dds.grupo4.tpimpacto.dtos.AceptarSolicitud;
import dds.grupo4.tpimpacto.entities.organizacion.*;
import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@CustomTestAnnotation
@FastTests
public class OrganizacionServiceTests {

    @Mock
    private OrganizacionRepository organizacionRepository;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private TipoConsumoService tipoConsumoService;

    private OrganizacionService organizacionService;

    private Usuario usuarioTest;
    private Miembro miembroTest;
    private Organizacion organizacionTest;
    private Sector sectorTest;
    private Solicitud solicitudTest;

    @BeforeEach
    public void setUp() {
        organizacionService = new OrganizacionServiceImpl(organizacionRepository, solicitudRepository, tipoConsumoService);

        usuarioTest = new Usuario("usernameTest", "passwordTest");
        miembroTest = new Miembro(null, usuarioTest);
        miembroTest.setId(1);
        organizacionTest = new Organizacion("organizacionTest", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO);
        organizacionTest.setId(2);
        sectorTest = new Sector("sectorTest", organizacionTest, null);
        sectorTest.setId(3);
        solicitudTest = new Solicitud(miembroTest, sectorTest, organizacionTest);
        solicitudTest.setId(4);
        organizacionTest.addSolicitud(solicitudTest);
        Mockito.when(solicitudRepository.getById(4)).thenReturn(solicitudTest);
    }

    @Test
    @Transactional
    public void aceptarSolicitud_cuandoExisteLaSolicitud_agregaAlMiembroAlSector() {
        AceptarSolicitud.Request request = new AceptarSolicitud.Request(4);

        AceptarSolicitud.Response response = organizacionService.aceptarSolicitud(request);

        Assertions.assertEquals(sectorTest.getId(), miembroTest.getSector().getId(), "El Miembro se asocio al Sector donde trabaja");
        Assertions.assertEquals(LocalDate.now(), miembroTest.getFechaIngreso());
        Assertions.assertEquals(organizacionTest.getId(), miembroTest.getOrganizacion().getId());
        Assertions.assertTrue(organizacionTest.getSolicitudes().isEmpty(),
                "La Solicitud aceptada se elimina de la lista de solicitudes de la Organizacion");
    }

    @Test
    @Transactional
    public void aceptarSolicitud_cuandoNoExisteLaSolicitud_tiraExcepcion() {
        Mockito.when(solicitudRepository.getById(99)).thenReturn(null);
        AceptarSolicitud.Request request = new AceptarSolicitud.Request(99);

        Assertions.assertThrows(IllegalArgumentException.class, () -> organizacionService.aceptarSolicitud(request));
    }

}
