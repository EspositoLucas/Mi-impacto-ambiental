package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.config.CustomTestAnnotation;
import dds.grupo4.tpimpacto.config.SlowTests;
import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.FactorDeEmision;
import dds.grupo4.tpimpacto.entities.medicion.TipoConsumo;
import dds.grupo4.tpimpacto.units.Cantidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@CustomTestAnnotation
@SlowTests
public class TipoConsumoRepositoryTests {

    private final TipoConsumoRepository tipoConsumoRepository;

    private TipoConsumo tipoConsumoTest;

    @Autowired
    public TipoConsumoRepositoryTests(TipoConsumoRepository tipoConsumoRepository) {
        this.tipoConsumoRepository = tipoConsumoRepository;
    }

    @BeforeEach
    public void buildTipoConsumoTest() {
        FactorDeEmision factorDeEmision = new FactorDeEmision(new Cantidad(null, 10d));
        tipoConsumoTest = new TipoConsumo("tipoConsumoTest", Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, "alcance1", null);
    }

    @Test
    @Transactional
    public void getByNombre_cuandoExisteTipoConsumoConNombre_retornaAlTipoConsumo() {
        tipoConsumoRepository.save(tipoConsumoTest);

        Optional<TipoConsumo> optionalTipoConsumoDeBD = tipoConsumoRepository.getByNombre("tipoConsumoTest");

        Assertions.assertTrue(optionalTipoConsumoDeBD.isPresent());
        Assertions.assertEquals("tipoConsumoTest", optionalTipoConsumoDeBD.get().getNombre());
        Assertions.assertEquals(Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, optionalTipoConsumoDeBD.get().getActividad());
        // Assertions.assertEquals(UnidadFactorEmision.G, optionalTipoConsumoDeBD.get().getUnidadFactorEmision());
        Assertions.assertEquals("alcance1", optionalTipoConsumoDeBD.get().getAlcance());
    }

    @Test
    @Transactional
    public void getByNombre_cuandoNoExisteTipoConsumoConNombre_retornaEmptyOptional() {
        Optional<TipoConsumo> optionalTipoConsumo = tipoConsumoRepository.getByNombre("tipoConsumoQueNoExiste");

        Assertions.assertFalse(optionalTipoConsumo.isPresent());
    }
}
