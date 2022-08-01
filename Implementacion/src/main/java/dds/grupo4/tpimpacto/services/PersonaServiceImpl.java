package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.organizacion.Persona;
import dds.grupo4.tpimpacto.repositories.PersonaRepository;

public class PersonaServiceImpl extends BaseServiceImpl<Persona, PersonaRepository> implements PersonaService {

    public PersonaServiceImpl(PersonaRepository repository) {
        super(repository);
    }
    
}
