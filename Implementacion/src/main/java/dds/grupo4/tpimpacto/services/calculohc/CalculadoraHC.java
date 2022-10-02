package dds.grupo4.tpimpacto.services.calculohc;

import dds.grupo4.tpimpacto.common.DateTimeService;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import dds.grupo4.tpimpacto.entities.medicion.RegistroCalculoHCDatoActividad;
import dds.grupo4.tpimpacto.entities.organizacion.Clasificacion;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.repositories.OrganizacionRepository;
import dds.grupo4.tpimpacto.services.RelacionUnidadesService;
import dds.grupo4.tpimpacto.services.UnidadService;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.units.Unidad;
import dds.grupo4.tpimpacto.utils.DateTimeUtils;
import dds.grupo4.tpimpacto.utils.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalculadoraHC {

    // Anio hardcodeado en el que suponemos se empezaron a tomar las mediciones
    // (se usa para calcular el HC total)
    private static final int ANIO_INICIO_CALCULOS = 2020;

    private final UnidadService unidadService;
    private final RelacionUnidadesService relacionUnidadesService;
    private final DateTimeService dateTimeService;
    private final OrganizacionRepository organizacionRepository;

    public CalculadoraHC(UnidadService unidadService, RelacionUnidadesService relacionUnidadesService, DateTimeService dateTimeService,
                         OrganizacionRepository organizacionRepository) {
        this.unidadService = unidadService;
        this.relacionUnidadesService = relacionUnidadesService;
        this.dateTimeService = dateTimeService;
        this.organizacionRepository = organizacionRepository;
    }

    public Cantidad calcularHCDatoActividadNoLogistica(Medicion medicion) {
        // HC = DatoActividad * FE (del TipoConsumo)
        return medicion.getValorNumerico()
                .times(medicion.getTipoConsumo().getFactorDeEmision().getCantidad(), relacionUnidadesService);
    }

    public Cantidad calcularHCDatoActividadLogistica(List<Medicion> mediciones) {
        // HC = Peso * K * Distancia * FE (del MedioTransporte)
        Organizacion organizacion = mediciones.stream().findAny().get().getOrganizacion();
        Cantidad peso = ListUtils.getMedicionConTipoConsumo(mediciones, "Peso total transportado")
                .getValorNumerico();
        Cantidad factorK = organizacion.getFactorK();
        Cantidad distancia = ListUtils.getMedicionConTipoConsumo(mediciones, "Distancia media recorrida")
                .getValorNumerico();
        Cantidad factorDeEmision = ListUtils.getMedicionConTipoConsumo(mediciones, "Medio de transporte")
                .getTipoMedioDeTransporte().getFactorDeEmision().getCantidad();
        return peso.times(factorK, relacionUnidadesService)
                .times(distancia, relacionUnidadesService)
                .times(factorDeEmision, relacionUnidadesService);
    }

    public Cantidad calcularHCAnualProrrateadoDatoActividad(Cantidad valorHC, int anioImputacion) {
        if (anioImputacion < dateTimeService.getCurrentDate().getYear()) {
            // Si el DatoActividad es para el 2021 y estamos en el 2022, entonces el HC es a anio completo,
            //  asi que se prorratea a 12 meses
            return valorHC.divide(12);
        }

        // Si el DatoActividad es para el anio actual, entonces se prorratea en (N-1) meses, donde N es el mes actual.
        //  Por ejemplo: si estamos en Abril, el valor se prorratea a 3 meses (Enero, Febrero y Marzo)
        // TODO: ver si se debería poder leer un dato Anual 2022 en Enero (habria que ver a que mes aplica)
        int mesActual = dateTimeService.getCurrentDate().getMonth().getValue();
        return valorHC.divide(mesActual - 1);
    }

    public Cantidad calcularHCBaseTramo(Tramo tramo) {
        // HC = Distancia * FE (del MedioTransporte)
        return tramo.getDistanciaRecorrida()
                .times(tramo.getMedioDeTransporte().getFactorDeEmision().getCantidad(), relacionUnidadesService);
    }

    public Cantidad calcularHCMensualTrayectoParaMiembro(Trayecto trayecto, Miembro miembro) {
        List<Tramo> tramosDelMiembro = trayecto.getTramosDelMiembro(miembro);
        List<Cantidad> hcsBasesTramos = tramosDelMiembro.stream()
                .map(this::calcularHCBaseTramo)
                .collect(Collectors.toList());
        Cantidad hcBaseTramos = hcsBasesTramos.stream()
                .reduce(Cantidad::add)
                .get();

        // HCsemanal = HCbase * PesoTrayecto * CantDiasXSemana
        Cantidad hcSemanal = hcBaseTramos
                .times(trayecto.getPesoTrayectoDelMiembro(miembro))
                .times(miembro.getOrganizacion().getCantDiasHabilesPorSemana());

        // HCmensual = HCsemanal * 4.5
        return hcSemanal.times(4.5);
    }

    ////////////////////////////////////////////////////////////////
    // A partir de aca estan todos los calculos para los reportes //
    ////////////////////////////////////////////////////////////////

    public Cantidad hcMensualDatosActividadOrganizacion(Organizacion organizacion, LocalDate mes) {
        RegistroCalculoHCDatoActividad registroMensual = organizacion.getRegistrosCalculoHCDatoActividad().stream()
                .filter(registro -> registro.getPeriodicidad() == Periodicidad.MENSUAL
                                    && registro.getPeriodoImputacion().isEqual(mes))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No hay ningun registro de HC mensual para la fecha " + DateTimeUtils.dateToString(mes, false)));
        Optional<RegistroCalculoHCDatoActividad> registroAnual = organizacion.getRegistrosCalculoHCDatoActividad().stream()
                .filter(registro -> registro.getPeriodicidad() == Periodicidad.ANUAL
                                    && registro.getPeriodoImputacion().getYear() == mes.getYear())
                .findFirst();

        Cantidad hcMensual = registroMensual.getValor();
        if (registroAnual.isPresent()) {
            // Si hay un valor Anual, es el valor prorrateado, asi que se suma al mensual
            hcMensual = hcMensual.add(registroAnual.get().getValor());
        }

        return hcMensual;
    }

    public Cantidad hcAnualDatosActividadOrganizacion(Organizacion organizacion, int anio) {
        // Si el Anio a calcular es el actual, los meses cerrados son (M - 1)
        // Si el Anio a calcular ya termino, los meses cerrados son 12
        int mesesCerradosEnAnio = dateTimeService.getCurrentDate().getYear() == anio
                ? dateTimeService.getCurrentDate().getMonthValue() - 1
                : 12;

        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcAnual = new Cantidad(GCO2eq, 0);
        for (int i = 1; i <= mesesCerradosEnAnio; i++) {
            LocalDate mes = LocalDate.of(anio, i, 1);
            Cantidad hcMensual = hcMensualDatosActividadOrganizacion(organizacion, mes);
            hcAnual = hcAnual.add(hcMensual);
        }
        return hcAnual;
    }

    public Cantidad hcTotalDatosActividadOrganizacion(Organizacion organizacion) {
        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcTotal = new Cantidad(GCO2eq, 0);
        for (int anio = ANIO_INICIO_CALCULOS; anio <= dateTimeService.getCurrentDate().getYear(); anio++) {
            Cantidad hcAnual = hcAnualDatosActividadOrganizacion(organizacion, anio);
            hcTotal = hcTotal.add(hcAnual);
        }
        return hcTotal;
    }

    public Cantidad hcMensualTrayectosOrganizacion(Organizacion organizacion, LocalDate mes) {
        List<Trayecto> trayectosDelMes = organizacion.getTrayectosRealizadosPorMiembrosEnFecha(mes);

        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcMensual = new Cantidad(GCO2eq, 0);
        for (Trayecto trayecto : trayectosDelMes) {
            // El HC de un Trayecto para la Organizacion es la suma de los HCs del Trayecto para los Miembros
            //  dividido la cantidad de Miembros de la Organizacion en ese Trayecto
            List<Miembro> miembrosDeOrganizacionEnTrayecto = trayecto.getMiembrosDeOrganizacion(organizacion);
            List<Cantidad> hcsDeMiembrosParaTrayecto = miembrosDeOrganizacionEnTrayecto.stream()
                    .map(miembro -> miembro.getHCDeTrayecto(trayecto))
                    .collect(Collectors.toList());
            Cantidad sumaHcsMiembros = unidadService.sumarCantidades(hcsDeMiembrosParaTrayecto, null);
            Cantidad hcParaOrganizacion = sumaHcsMiembros.divide(miembrosDeOrganizacionEnTrayecto.size());
            hcMensual = hcMensual.add(hcParaOrganizacion);
        }
        return hcMensual;
    }

    public Cantidad hcAnualTrayectosOrganizacion(Organizacion organizacion, int anio) {
        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcAnual = new Cantidad(GCO2eq, 0);
        for (int i = 1; i <= 12; i++) {
            LocalDate mes = LocalDate.of(anio, i, 1);
            Cantidad hcMensual = hcMensualTrayectosOrganizacion(organizacion, mes);
            hcAnual = hcAnual.add(hcMensual);
        }
        return hcAnual;
    }

    public Cantidad hcTotalTrayectosOrganizacion(Organizacion organizacion) {
        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcTotal = new Cantidad(GCO2eq, 0);
        for (int anio = ANIO_INICIO_CALCULOS; anio <= dateTimeService.getCurrentDate().getYear(); anio++) {
            Cantidad hcAnual = hcAnualTrayectosOrganizacion(organizacion, anio);
            hcTotal = hcTotal.add(hcAnual);
        }
        return hcTotal;
    }

    public Cantidad hcAnualOrganizacion(Organizacion organizacion, int anio) {
        return hcAnualDatosActividadOrganizacion(organizacion, anio)
                .add(hcAnualTrayectosOrganizacion(organizacion, anio));
    }

    public Cantidad hcTotalOrganizacion(Organizacion organizacion) {
        return hcTotalDatosActividadOrganizacion(organizacion)
                .add(hcTotalTrayectosOrganizacion(organizacion));
    }

    public Cantidad hcMensualTrayectosMiembro(Miembro miembro, LocalDate mes) {
        List<Cantidad> hcsTrayectosEnMes = miembro.getTrayectosRealizadosEnFecha(mes).stream()
                .map(miembro::getHCDeTrayecto)
                .collect(Collectors.toList());
        return unidadService.sumarCantidades(hcsTrayectosEnMes, "gCO2eq");
    }

    public Cantidad hcAnualTrayectosMiembro(Miembro miembro, int anio) {
        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcAnual = new Cantidad(GCO2eq, 0);
        for (int i = 1; i <= 12; i++) {
            LocalDate mes = LocalDate.of(anio, i, 1);
            Cantidad hcMensual = hcMensualTrayectosMiembro(miembro, mes);
            hcAnual = hcAnual.add(hcMensual);
        }
        return hcAnual;
    }

    public Map<Organizacion, Cantidad> hcAnualSectorTerritorialSeparadoPorOrganizacion(SectorTerritorial sectorTerritorial, int anio) {
        Map<Organizacion, Cantidad> hcsPorOrganizacion = new HashMap<>();
        for (Organizacion organizacion : sectorTerritorial.getOrganizaciones()) {
            Cantidad hcOrganizacion = hcAnualOrganizacion(organizacion, anio);
            hcsPorOrganizacion.put(organizacion, hcOrganizacion);
        }
        return hcsPorOrganizacion;
    }

    public Map<Organizacion, Cantidad> hcTotalSectorTerritorialSeparadoPorOrganizacion(SectorTerritorial sectorTerritorial) {
        Map<Organizacion, Cantidad> hcsTotalesPorOrganizacion = new HashMap<>();
        for (int anio = ANIO_INICIO_CALCULOS; anio <= dateTimeService.getCurrentDate().getYear(); anio++) {
            Map<Organizacion, Cantidad> hcsAnualesPorOrganizacion = hcAnualSectorTerritorialSeparadoPorOrganizacion(sectorTerritorial, anio);
            hcsAnualesPorOrganizacion.forEach((organizacion, hcAnual) -> hcsTotalesPorOrganizacion.merge(
                    organizacion,
                    hcAnual,
                    Cantidad::add));
        }
        return hcsTotalesPorOrganizacion;
    }

    public Cantidad hcAnualSectorTerritorial(SectorTerritorial sectorTerritorial, int anio) {
        Map<Organizacion, Cantidad> hcsPorOrganizacion = hcAnualSectorTerritorialSeparadoPorOrganizacion(sectorTerritorial, anio);
        return hcsPorOrganizacion.values().stream()
                .reduce(Cantidad::add).get();
    }

    public Cantidad hcTotalSectorTerritorial(SectorTerritorial sectorTerritorial) {
        Unidad GCO2eq = unidadService.getBySimbolo("gCO2eq").get();
        Cantidad hcTotal = new Cantidad(GCO2eq, 0);
        for (int anio = ANIO_INICIO_CALCULOS; anio <= dateTimeService.getCurrentDate().getYear(); anio++) {
            Cantidad hcAnual = hcAnualSectorTerritorial(sectorTerritorial, anio);
            hcTotal = hcTotal.add(hcAnual);
        }
        return hcTotal;
    }

    @Transactional
    public Cantidad hcAnualClasificacionDeOrganizacion(Clasificacion clasificacion, int anio) {
        List<Organizacion> organizacionesConClasificacion = organizacionRepository.getOrganizacionesConClasificacion(clasificacion);
        List<Cantidad> hcsOrganizaciones = organizacionesConClasificacion.stream()
                .map(organizacion -> hcAnualOrganizacion(organizacion, anio))
                .collect(Collectors.toList());
        return unidadService.sumarCantidades(hcsOrganizaciones, "gCO2eq");
    }

    public Map<Clasificacion, Cantidad> hcAnualSeparadoPorClasificacionDeOrganizacion(int anio) {
        Map<Clasificacion, Cantidad> hcsPorClasificacion = new HashMap<>();
        for (Clasificacion clasificacion : Clasificacion.values()) {
            Cantidad hcClasificacion = hcAnualClasificacionDeOrganizacion(clasificacion, anio);
            hcsPorClasificacion.put(clasificacion, hcClasificacion);
        }
        return hcsPorClasificacion;
    }

    public Map<Clasificacion, Cantidad> hcTotalSeparadoPorClasificacionDeOrganizacion() {
        Map<Clasificacion, Cantidad> hcsTotalesPorClasificacion = new HashMap<>();
        for (int anio = ANIO_INICIO_CALCULOS; anio <= dateTimeService.getCurrentDate().getYear(); anio++) {
            Map<Clasificacion, Cantidad> hcsAnualesPorClasificacion = hcAnualSeparadoPorClasificacionDeOrganizacion(anio);
            hcsAnualesPorClasificacion.forEach((clasificacion, hcAnual) -> hcsTotalesPorClasificacion.merge(
                    clasificacion,
                    hcAnual,
                    Cantidad::add
            ));
        }
        return hcsTotalesPorClasificacion;
    }

    /*
    //Fijarse la repeticion
    public double impactoHCMiembroMensual(Miembro miembro, LocalDate mesElegido) {
        double calculoHCDeOrg = this.calcularHCOrganizacionMensual(miembro.getOrganizacion(), mesElegido);
        double calculoHCMiembroMensual = this.calcularHCMiembroMensual(miembro, mesElegido);
        double impacto = calculoHCMiembroMensual * 100 / calculoHCDeOrg;
        return impacto;
    }

    public double impactoHCMiembroAnual(Miembro miembro, int anio) {
        double calculoHCDeOrg = this.calcularHCOrganizacionAnual(miembro.getOrganizacion(), anio);
        double calculoHCMiembroAnual = this.calcularHCMiembroAnual(miembro, anio);
        double impacto = calculoHCMiembroAnual * 100 / calculoHCDeOrg;
        return impacto;
    }

    public double calcularHCSectorPromedioMensual(Sector sector, LocalDate mesElegido) {
        return calcularHCOrganizacionMensual(sector.getOrganizacion(), mesElegido) / sector.getMiembros().size();
    }

    public double calculoHCSectorPromedioAnual(Sector sector, int anio) {
        return calcularHCOrganizacionAnual(sector.getOrganizacion(), anio) / sector.getMiembros().size();
    }

    */

}
