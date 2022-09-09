package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.SlowTests;
import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.organizacion.TipoOrganizacion;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Espacio;
import dds.grupo4.tpimpacto.entities.trayecto.TipoEspacio;
import dds.grupo4.tpimpacto.units.Cantidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@CustomTestAnnotation
@SlowTests
public class SectorRepositoryTests {

    private final SectorRepository sectorRepository;

    private Sector sectorTest;

    @Autowired
    public SectorRepositoryTests(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @BeforeEach
    public void buildSectorTest() {
        Organizacion organizacionTest = new Organizacion("organizacionTest", TipoOrganizacion.EMPRESA,
                Clasificacion.EMPRESA_SECTOR_PRIMARIO, new Cantidad(null, 1), 5);
        Direccion direccionTest = new Direccion("calle", "altura", "pais", "provincia",
                "municipio", "localidad", "barrio", 1234);
        Espacio espacioTest = new Espacio(direccionTest, "espacioTest", TipoEspacio.TRABAJO);
        sectorTest = new Sector("sectorTest", organizacionTest, espacioTest);
    }

    @Test
    @Transactional
    public void getByNombreYOrganizacion_cuandoExisteSectorConNombreYOrganizacion_retornaAlSector() {
        sectorRepository.save(sectorTest);

        Optional<Sector> optionalSectorDeBD = sectorRepository.getByNombreYOrganizacion(
                "sectorTest",
                "organizacionTest"
        );

        Assertions.assertTrue(optionalSectorDeBD.isPresent());
        Assertions.assertEquals("sectorTest", optionalSectorDeBD.get().getNombre());
        Assertions.assertEquals("organizacionTest", optionalSectorDeBD.get().getOrganizacion().getRazonSocial());
        Assertions.assertEquals("espacioTest", optionalSectorDeBD.get().getEspacio().getNombre());

        Direccion direccionDeEspacioDeBD = optionalSectorDeBD.get().getEspacio().getDireccion();
        Assertions.assertEquals("calle", direccionDeEspacioDeBD.getCalle());
        Assertions.assertEquals(1234, direccionDeEspacioDeBD.getCodigoPostal());
    }

    @Test
    @Transactional
    public void getByNombreYOrganizacion_cuandoNoExisteSectorConNombreYOrganizacion_retornaEmptyOptional() {
        Optional<Sector> optionalSectorDeBD = sectorRepository.getByNombreYOrganizacion(
                "unSectorQueNoExiste",
                "unaOrganizacionQueNoExiste"
        );

        Assertions.assertFalse(optionalSectorDeBD.isPresent());
    }
}
