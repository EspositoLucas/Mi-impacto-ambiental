package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.FastTests;
import dds.grupo4.tpimpacto.dtos.CrearMiembroRequest;
import dds.grupo4.tpimpacto.dtos.CrearMiembroResponse;
import dds.grupo4.tpimpacto.entities.organizacion.*;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import dds.grupo4.tpimpacto.repositories.PersonaRepository;
import dds.grupo4.tpimpacto.repositories.SectorRepository;
import dds.grupo4.tpimpacto.repositories.SolicitudRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

@CustomTestAnnotation
@FastTests
public class MiembroServiceTests {

    @Mock
    private MiembroRepository miembroRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private SectorRepository sectorRepository;

    private MiembroService miembroService;

    @BeforeEach
    public void setUp() {
        miembroService = new MiembroService(miembroRepository, personaRepository, solicitudRepository, sectorRepository);

        Persona personaTest = new Persona("nombrePersonaTest", "apellidoPersonaTest", TipoDocumento.DNI, "documentoPersonaTest");
        personaTest.setId(1);
        Mockito.when(personaRepository.getById(1)).thenReturn(personaTest);

        Organizacion organizacionTest = new Organizacion("organizacionTest", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, 5);
        organizacionTest.setId(2);
        Sector sectorTest = new Sector("sectorTest", organizacionTest, null);
        sectorTest.setId(3);
        Mockito.when(sectorRepository.getById(3)).thenReturn(sectorTest);
    }

    @Test
    @Transactional
    public void crearMiembro_cuandoExistenTodosLosDatos_creaAlMiembroYLaSolicitudDeVinculacion() {
        CrearMiembroRequest request = new CrearMiembroRequest(1, 2, 3);
        ArgumentCaptor<Solicitud> captor = ArgumentCaptor.forClass(Solicitud.class);

        CrearMiembroResponse response = miembroService.crearMiembro(request);

        Mockito.verify(solicitudRepository).save(captor.capture());
        Solicitud solicitudGenerada = captor.getValue();
        Assertions.assertEquals(1, solicitudGenerada.getMiembro().getPersona().getId());
        Assertions.assertEquals(2, solicitudGenerada.getSector().getOrganizacion().getId());
        Assertions.assertEquals(3, solicitudGenerada.getSector().getId());
    }

    @Test
    @Transactional
    public void crearMiembro_cuandoElSectorNoPerteneceALaOrganizacion_tiraExcepcion() {
        Organizacion organizacion = new Organizacion("organizacionTest", TipoOrganizacion.EMPRESA, Clasificacion.EMPRESA_SECTOR_PRIMARIO, null, 5);
        organizacion.setId(10);
        Sector sector = new Sector("sectorTest", organizacion, null);
        sector.setId(20);
        Mockito.when(sectorRepository.getById(20)).thenReturn(sector);
        int idSector = 20, idOrganizacion = 99; // El idOrganizacion correcto seria el 10, lo pongo mal a proposito

        CrearMiembroRequest request = new CrearMiembroRequest(1, idOrganizacion, idSector);

        Assertions.assertThrows(IllegalArgumentException.class, () -> miembroService.crearMiembro(request));
    }
}
