package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import dds.grupo4.tpimpacto.units.Unidad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UnidadRepository extends BaseRepository<Unidad> {

    public UnidadRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Unidad> getBySimbolo(String simbolo) {
        String query = "from Unidad u where u.simbolo = :simbolo";
        return entityManager.createQuery(query, Unidad.class)
                .setParameter("simbolo", simbolo)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<Unidad> getEntityClass() {
        return Unidad.class;
    }
}
