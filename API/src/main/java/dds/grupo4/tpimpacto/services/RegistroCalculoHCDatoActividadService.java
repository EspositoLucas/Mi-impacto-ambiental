package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import dds.grupo4.tpimpacto.entities.medicion.RegistroCalculoHCDatoActividad;
import dds.grupo4.tpimpacto.repositories.RegistroCalculoHCDatoActividadRepository;
import dds.grupo4.tpimpacto.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RegistroCalculoHCDatoActividadService extends BaseService<RegistroCalculoHCDatoActividad, RegistroCalculoHCDatoActividadRepository> {
    public RegistroCalculoHCDatoActividadService(RegistroCalculoHCDatoActividadRepository repository) {
        super(repository);
    }

    public Optional<RegistroCalculoHCDatoActividad> getRegistroCalculoHCParaPeriodo(long idOrganizacion, Periodicidad periodicidad, LocalDate periodo) {
        return repository.getRegistroCalculoHCParaPeriodo(idOrganizacion, periodicidad, periodo);
    }
}
