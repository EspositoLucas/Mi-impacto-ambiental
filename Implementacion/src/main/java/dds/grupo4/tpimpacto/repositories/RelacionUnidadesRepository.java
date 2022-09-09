package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.units.RelacionUnidades;
import dds.grupo4.tpimpacto.units.Unidad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
        if (izquierda != null) {
            condiciones.add("relacion.unidadIzquierda.simbolo = " + izquierda.getSimbolo());
        }
        if (derecha != null) {
            condiciones.add("relacion.unidadDerecha.simbolo = " + derecha.getSimbolo());
        }
        if (producto != null) {
            condiciones.add("relacion.unidadProducto.simbolo = " + producto.getSimbolo());
        }
        if (cociente != null) {
            condiciones.add("relacion.unidadCociente.simbolo = " + cociente.getSimbolo());
        }
        String joinedCondiciones = String.join(" AND ", condiciones);
        String query = "FROM RelacionUnidades relacion WHERE " + joinedCondiciones;

        return entityManager.createQuery(query, RelacionUnidades.class)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<RelacionUnidades> getEntityClass() {
        return RelacionUnidades.class;
    }
}
