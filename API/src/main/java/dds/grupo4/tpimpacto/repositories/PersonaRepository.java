package dds.grupo4.tpimpacto.repositories;

import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PersonaRepository extends BaseRepository<Persona> {

    public PersonaRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Persona> getEntityClass() {
        return Persona.class;
    }

}
