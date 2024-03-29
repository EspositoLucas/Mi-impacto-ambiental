package dds.grupo4.tpimpacto.services;

import dds.grupo4.tpimpacto.dtos.TramoDto;
import dds.grupo4.tpimpacto.dtos.TrayectoDto;
import dds.grupo4.tpimpacto.dtos.base.ResponseWithResults;
import dds.grupo4.tpimpacto.entities.medicion.RegistroCalculoHCTrayecto;
import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.trayecto.Lugar;
import dds.grupo4.tpimpacto.entities.trayecto.MiembroPorTrayecto;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.repositories.LugarRepository;
import dds.grupo4.tpimpacto.repositories.MedioDeTransporteRepository;
import dds.grupo4.tpimpacto.repositories.MiembroRepository;
import dds.grupo4.tpimpacto.repositories.TrayectoRepository;
import dds.grupo4.tpimpacto.services.base.BaseService;
import dds.grupo4.tpimpacto.services.calculodistancias.CalculadoraDistancias;
import dds.grupo4.tpimpacto.services.calculohc.CalculadoraHC;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.utils.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrayectoService extends BaseService<Trayecto, TrayectoRepository> {

    private final LugarRepository lugarRepository;
    private final MiembroRepository miembroRepository;
    private final MedioDeTransporteRepository medioDeTransporteRepository;
    private final CalculadoraDistancias calculadoraDistancias;
    private final CalculadoraHC calculadoraHC;

    public TrayectoService(TrayectoRepository repository, LugarRepository lugarRepository, MiembroRepository miembroRepository, MedioDeTransporteRepository medioDeTransporteRepository, CalculadoraDistancias calculadoraDistancias, CalculadoraHC calculadoraHC) {
        super(repository);
        this.lugarRepository = lugarRepository;
        this.miembroRepository = miembroRepository;
        this.medioDeTransporteRepository = medioDeTransporteRepository;
        this.calculadoraDistancias = calculadoraDistancias;
        this.calculadoraHC = calculadoraHC;
    }

    @Transactional
    public void crearTrayecto(TrayectoDto trayectoDto) {
        Lugar lugarInicio = lugarRepository.getById(trayectoDto.getLugarInicio().getId());
        Lugar lugarFin = lugarRepository.getById(trayectoDto.getLugarFin().getId());

        LocalDate fechaInicio = DateTimeUtils.dateWithOnlyYearAndMonth(trayectoDto.getFechaInicio());
        LocalDate fechaFin = DateTimeUtils.dateWithOnlyYearAndMonth(trayectoDto.getFechaFin());

        Trayecto trayecto = new Trayecto(lugarInicio, lugarFin, fechaInicio, fechaFin);

        List<MiembroPorTrayecto> miembros = trayectoDto.getMiembrosPorTrayecto().stream()
                .map(miembroPorTrayectoDto -> new MiembroPorTrayecto(
                        miembroRepository.getById(miembroPorTrayectoDto.getMiembro().getId()),
                        trayecto,
                        miembroPorTrayectoDto.getPeso()
                ))
                .collect(Collectors.toList());
        trayecto.addMiembros(miembros);

        for (TramoDto tramoDto : trayectoDto.getTramos()) {
            Tramo tramo = crearTramo(trayecto, tramoDto);
            trayecto.addTramo(tramo);
        }
        this.save(trayecto);

        calcularHCTrayectoYGuardarRegistroCalculo(trayecto);
    }

    @Transactional
    public ResponseWithResults<TrayectoDto> listarTrayectos() {
        List<TrayectoDto> dtos = this.getAll().stream()
                .map(TrayectoDto::from)
                .collect(Collectors.toList());
        return new ResponseWithResults<>(HttpStatus.OK, dtos);
    }

    private Tramo crearTramo(Trayecto trayecto, TramoDto tramoDto) {
        MedioDeTransporte medioDeTransporte = medioDeTransporteRepository.getById(tramoDto.getMedioDeTransporte().getId());
        Lugar lugarInicio = lugarRepository.getById(tramoDto.getLugarInicio().getId());
        Lugar lugarFin = lugarRepository.getById(tramoDto.getLugarFin().getId());
        List<Miembro> miembros = tramoDto.getMiembros().stream()
                .map(idTextPair -> miembroRepository.getById(idTextPair.getId()))
                .collect(Collectors.toList());

        Tramo tramo = new Tramo(trayecto, medioDeTransporte, lugarInicio, lugarFin);
        tramo.addMiembros(miembros);
        tramo.calcularDistanciaRecorrida(calculadoraDistancias);
        return tramo;
    }

    private void calcularHCTrayectoYGuardarRegistroCalculo(Trayecto trayecto) {
        for (MiembroPorTrayecto miembroPorTrayecto : trayecto.getMiembrosPorTrayecto()) {
            Miembro miembro = miembroPorTrayecto.getMiembro();
            Cantidad hcMensualTrayectoParaMiembro = calculadoraHC.calcularHCMensualTrayectoParaMiembro(trayecto, miembro);
            RegistroCalculoHCTrayecto registroCalculoHCTrayecto = new RegistroCalculoHCTrayecto(miembro, hcMensualTrayectoParaMiembro);
            trayecto.addRegistroCalculoHCTrayecto(registroCalculoHCTrayecto);
        }
    }
}
