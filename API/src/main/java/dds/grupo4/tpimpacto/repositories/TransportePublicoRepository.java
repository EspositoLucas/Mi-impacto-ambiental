package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.medioTransporte.TransportePublico;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class TransportePublicoRepository extends BaseRepository<TransportePublico> {

    public TransportePublicoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<TransportePublico> getByLinea(String linea) {
        String query = "FROM TransportePublico transportePublico " +
                "WHERE transportePublico.linea = :linea";
        return entityManager.createQuery(query, TransportePublico.class)
                .setParameter("linea", linea)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<TransportePublico> getEntityClass() {
        return TransportePublico.class;
    }
}
