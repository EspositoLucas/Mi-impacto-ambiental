package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.SlowTests;
import dds.grupo4.tpimpacto.entities.geo.Localidad;
import dds.grupo4.tpimpacto.entities.geo.Municipio;
import dds.grupo4.tpimpacto.entities.geo.Pais;
import dds.grupo4.tpimpacto.entities.geo.Provincia;
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
        Pais paisTest = new Pais(1, "PaisTest");
        Provincia provinciaTest = new Provincia(2, "ProvinciaTest");
        Municipio municipioTest = new Municipio(3, "MunicipioTest");
        Localidad localidadTest = new Localidad(4, "LocalidadTest", "1234");
        Direccion direccionTest = new Direccion("calle", "altura");
        municipioTest.addLocalidad(localidadTest);
        provinciaTest.addMunicipio(municipioTest);
        paisTest.addProvincia(provinciaTest);
        localidadTest.addDireccion(direccionTest);

        Organizacion organizacionTest = new Organizacion("organizacionTest", TipoOrganizacion.EMPRESA,
                Clasificacion.EMPRESA_SECTOR_PRIMARIO, new Cantidad(null, 1), 5);
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
        Assertions.assertEquals("1234", direccionDeEspacioDeBD.getCodigoPostal());
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
