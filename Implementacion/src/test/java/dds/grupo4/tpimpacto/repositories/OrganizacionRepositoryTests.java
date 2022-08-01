package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.SlowTests;
import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Contacto;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.TipoOrganizacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@CustomTestAnnotation
@SlowTests
public class OrganizacionRepositoryTests {

    private final OrganizacionRepository organizacionRepository;

    private Organizacion organizacionTest;

    @Autowired
    public OrganizacionRepositoryTests(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    @BeforeEach
    public void buildOrganizacionTest() {
        organizacionTest = new Organizacion("organizacionTest", TipoOrganizacion.EMPRESA,
                Clasificacion.EMPRESA_SECTOR_PRIMARIO);
    }

    @Test
    @Transactional
    public void getByRazonSocial_cuandoExisteOrganizacionConRazonSocial_retornaLaOrganizacion() {
        organizacionRepository.save(organizacionTest);

        Optional<Organizacion> optionalOrganizacionDeBD = organizacionRepository.getByRazonSocial("organizacionTest");

        Assertions.assertTrue(optionalOrganizacionDeBD.isPresent());
        Assertions.assertEquals("organizacionTest", optionalOrganizacionDeBD.get().getRazonSocial());
        Assertions.assertEquals(TipoOrganizacion.EMPRESA, optionalOrganizacionDeBD.get().getTipoOrganizacion());
        Assertions.assertEquals(Clasificacion.EMPRESA_SECTOR_PRIMARIO, optionalOrganizacionDeBD.get().getClasificacion());
    }

    @Test
    @Transactional
    public void getByRazonSocial_cuandoNoExisteOrganizacionConRazonSocial_retornaEmptyOptional() {
        Optional<Organizacion> optionalOrganizacionDeBD = organizacionRepository.getByRazonSocial("unaOrganizacionQueNoExiste");

        Assertions.assertFalse(optionalOrganizacionDeBD.isPresent());
    }

    @Test
    @Transactional
    public void getMailsDeContactos_cuandoHayContactosGuardados_retornaListaDeMails() {
        Contacto contacto1 = new Contacto("contacto1", "contacto1", "contacto1@gmail.com", "12345");
        Contacto contacto2 = new Contacto("contacto2", "contacto2", "contacto2@gmail.com", "12345");
        organizacionTest.addContacto(contacto1);
        organizacionTest.addContacto(contacto2);

        organizacionRepository.save(organizacionTest);

        List<String> mails = organizacionRepository.getMailsDeContactos();

        Assertions.assertEquals(2, mails.size());
        Assertions.assertTrue(mails.contains("contacto1@gmail.com"));
        Assertions.assertTrue(mails.contains("contacto2@gmail.com"));
    }
}
