package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.seguridad.Usuario;
import dds.grupo4.tpimpacto.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Usuario> getByUsername(String username) {
        String query = "FROM Usuario u " +
                "WHERE u.username = :username";
        return entityManager.createQuery(query, Usuario.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Class<Usuario> getEntityClass() {
        return Usuario.class;
    }
}
