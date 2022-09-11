package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.units.RelacionUnidades;
import dds.grupo4.tpimpacto.units.Unidad;
import org.javatuples.Pair;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RelacionUnidadesRepository extends BaseRepository<RelacionUnidades> {

    public RelacionUnidadesRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<RelacionUnidades> getByUnidades(Unidad izquierda, Unidad derecha, Unidad producto, Unidad cociente) {
        List<String> condiciones = new ArrayList<>();
        List<Pair<String, Object>> parameters = new ArrayList<>();
        if (izquierda != null) {
            condiciones.add("relacion.unidadIzquierda.simbolo = :simboloUnidadIzquierda");
            parameters.add(Pair.with("simboloUnidadIzquierda", izquierda.getSimbolo()));
        }
        if (derecha != null) {
            condiciones.add("relacion.unidadDerecha.simbolo = :simboloUnidadDerecha");
            parameters.add(Pair.with("simboloUnidadDerecha", derecha.getSimbolo()));
        }
        if (producto != null) {
            condiciones.add("relacion.unidadProducto.simbolo = :simboloUnidadProducto");
            parameters.add(Pair.with("simboloUnidadProducto", producto.getSimbolo()));
        }
        if (cociente != null) {
            condiciones.add("relacion.unidadCociente.simbolo = :simboloUnidadCociente");
            parameters.add(Pair.with("simboloUnidadCociente", cociente.getSimbolo()));
        }
        String joinedCondiciones = String.join(" AND ", condiciones);
        String qlString = "FROM RelacionUnidades relacion WHERE " + joinedCondiciones;

        TypedQuery<RelacionUnidades> typedQuery = entityManager.createQuery(qlString, RelacionUnidades.class);
        parameters.forEach(p -> typedQuery.setParameter(p.getValue0(), p.getValue1()));
        return typedQuery
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<RelacionUnidades> getEntityClass() {
        return RelacionUnidades.class;
    }
}
