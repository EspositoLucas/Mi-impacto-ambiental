package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.repositories.RelacionUnidadesRepository;
import dds.grupo4.tpimpacto.units.RelacionUnidades;
import dds.grupo4.tpimpacto.units.Unidad;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RelacionUnidadesService extends BaseService<RelacionUnidades, RelacionUnidadesRepository> {

    public RelacionUnidadesService(RelacionUnidadesRepository repository) {
        super(repository);
    }

    @Transactional
    public Pair<Unidad, Boolean> getUnidadResultanteDeProducto(Unidad left, Unidad right) {
        // Algo sin unidad * algo con unidad resulta en la misma unidad
        if (left == null)
            return Pair.with(right, true);
        if (right == null)
            return Pair.with(left, true);

        Optional<RelacionUnidades> relacionDirecta = repository.getByUnidades(left, right, null, null);
        if (relacionDirecta.isPresent())
            return Pair.with(relacionDirecta.get().getUnidadProducto(), true);

        // Por ejemplo, [kg * (gCO2eq/kg)] da gCO2eq, donde "kg" seria la UnidadDerecha de la relacion con "gCO2eq"
        // y "gCO2eq/kg" seria la UnidadCociente, y la unidad resultado seria "gCO2eq" (la UnidadIzquierda)
        Optional<RelacionUnidades> relacionIndirectaPorCociente = repository.getByUnidades(null, left, null, right);
        if (relacionIndirectaPorCociente.isPresent())
            return Pair.with(relacionIndirectaPorCociente.get().getUnidadIzquierda(), true);

        return Pair.with(null, false);
    }
}
