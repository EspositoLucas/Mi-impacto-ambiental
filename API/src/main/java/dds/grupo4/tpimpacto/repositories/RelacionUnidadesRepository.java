package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.units.RelacionUnidades;
import dds.grupo4.tpimpacto.units.RelacionUnidades_;
import dds.grupo4.tpimpacto.units.Unidad;
import dds.grupo4.tpimpacto.units.Unidad_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RelacionUnidadesRepository extends BaseRepository<RelacionUnidades> {

    public RelacionUnidadesRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<RelacionUnidades> getByUnidades(Unidad izquierda, Unidad derecha, Unidad producto, Unidad cociente) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<RelacionUnidades> criteria = builder.createQuery(RelacionUnidades.class);
        Root<RelacionUnidades> root = criteria.from(RelacionUnidades.class);

        List<Predicate> condiciones = new ArrayList<>();
        if (izquierda != null) {
            Join<RelacionUnidades, Unidad> joinUnidadIzquierda = root.join(RelacionUnidades_.unidadIzquierda);
            condiciones.add(
                    builder.equal(joinUnidadIzquierda.get(Unidad_.simbolo), izquierda.getSimbolo())
            );
        }
        if (derecha != null) {
            Join<RelacionUnidades, Unidad> joinUnidadDerecha = root.join(RelacionUnidades_.unidadDerecha);
            condiciones.add(
                    builder.equal(joinUnidadDerecha.get(Unidad_.simbolo), derecha.getSimbolo())
            );
        }
        if (producto != null) {
            Join<RelacionUnidades, Unidad> joinUnidadProducto = root.join(RelacionUnidades_.unidadProducto);
            condiciones.add(
                    builder.equal(joinUnidadProducto.get(Unidad_.simbolo), producto.getSimbolo())
            );
        }
        if (cociente != null) {
            Join<RelacionUnidades, Unidad> joinUnidadCociente = root.join(RelacionUnidades_.unidadCociente);
            condiciones.add(
                    builder.equal(joinUnidadCociente.get(Unidad_.simbolo), cociente.getSimbolo())
            );
        }

        criteria.select(root)
                .where(condiciones.toArray(new Predicate[0]));

        return entityManager.createQuery(criteria)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<RelacionUnidades> getEntityClass() {
        return RelacionUnidades.class;
    }
}
