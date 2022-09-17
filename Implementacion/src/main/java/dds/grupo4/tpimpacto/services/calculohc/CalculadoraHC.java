package dds.grupo4.tpimpacto.services.calculohc;

import dds.grupo4.tpimpacto.common.DateTimeService;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;
import dds.grupo4.tpimpacto.services.RelacionUnidadesService;
import dds.grupo4.tpimpacto.units.Cantidad;
import dds.grupo4.tpimpacto.utils.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculadoraHC {

    private final RelacionUnidadesService relacionUnidadesService;
    private final DateTimeService dateTimeService;

    public CalculadoraHC(RelacionUnidadesService relacionUnidadesService, DateTimeService dateTimeService) {
        this.relacionUnidadesService = relacionUnidadesService;
        this.dateTimeService = dateTimeService;
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
                .getMedioDeTransporte().getFactorDeEmision().getCantidad();
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

        // HC mensual = HCsemanal * 4.5
        return hcSemanal.times(4.5);
    }

    /*
    public double calcularHCMiembroMensual(Miembro miembro, LocalDate mesElegido) {
        organizacionCalculo = miembro.getOrganizacion();
        List<Tramo> tramosEnMesElegido = miembro.getTramos().stream().filter(t -> t.getTrayecto().seRealizaEntre(mesElegido)).collect(Collectors.toList());
        double hcTramosMensual = tramosEnMesElegido.stream().mapToDouble(this::calcularHCTramoMensual).sum();

        return hcTramosMensual;
    }

    public double calcularHCMiembroAnual(Miembro miembro, int anio) {
        List<LocalDate> meses = null;
        int mesActual;
        if (LocalDate.now().getYear() == anio) mesActual = LocalDate.now().getMonthValue();
        else mesActual = 12;

        for (int i = 1; i <= mesActual-1; i++) {
            meses.add(LocalDate.of(anio, i, 1));
        }


        return meses.stream().mapToDouble(mes -> this.calcularHCMiembroMensual(miembro, mes)).sum();
    }

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

    public double calcularHCOrganizacionMensual(Organizacion organizacion, LocalDate mesElegido) {
        organizacionCalculo = organizacion;
        List<Tramo> tramosEnMesElegido = organizacion.getTramosDeMiembros().stream().filter(t -> t.getTrayecto().seRealizaEntre(mesElegido)).collect(Collectors.toList());
        double hcTramosMensual = tramosEnMesElegido.stream().mapToDouble(this::calcularHCTramoMensual).sum();
        List<Medicion> actividadesMensuales = organizacion.getMediciones().stream().filter(m -> m.getPeriodicidad().equals(Periodicidad.MENSUAL)).collect(Collectors.toList());
        double hcActividadesMensual = actividadesMensuales.stream().mapToDouble((this::calcularHCDatoActividad)).sum() ;
        //Calcular hc de logistica
        this.calcularHCActividadLogistica(actividadesMensuales.stream().filter(m -> m.getActividad() == Actividad.LogisticaDeProductosYResiduos).collect(Collectors.toList()));

        return hcTramosMensual + hcActividadesMensual;
    }

    public double calcularHCActividadLogistica(List<Medicion> mediciones) {
        String medioTransporte = mediciones.stream().filter(elememt -> elememt.getTipoConsumo().getNombre().equals("Medio de transporte")).collect(Collectors.toList()).get(0).getValor();

        Optional<FactorDeEmision> factorDeEmision = factorDeEmisionRepository.getByTipoDeTransporte(TipoMedioTransporte.valueOf(medioTransporte));

        Double distancia = Double.valueOf(mediciones.stream().filter(elememt -> elememt.getTipoConsumo().getNombre().equals("Distancia media recorrida")).collect(Collectors.toList()).get(0).getValor());
        Double peso = Double.valueOf(mediciones.stream().filter(elememt -> elememt.getTipoConsumo().getNombre().equals("Peso total transportado")).collect(Collectors.toList()).get(0).getValor());
        return peso * distancia * factorDeEmision.get().getValor() * organizacionCalculo.getFactorK();
    }

    //Revisar lo de las fechas
    public double calcularHCOrganizacionAnual(Organizacion organizacion, int anio) {
        int mesActual;
        if (LocalDate.now().getYear() == anio) mesActual = LocalDate.now().getMonthValue() -1;
        else mesActual = 12;


        List<Medicion> actividadesAnuales = organizacion.getMediciones().stream().filter(m -> m.getPeriodicidad().equals(Periodicidad.ANUAL)).collect(Collectors.toList());
        double hcActividadesAnual = actividadesAnuales.stream().mapToDouble((this::calcularHCDatoActividad)).sum() ;

        //Calcular hc anual de logistica
        this.calcularHCActividadLogistica(actividadesAnuales.stream().filter(m -> m.getActividad() == Actividad.LogisticaDeProductosYResiduos).collect(Collectors.toList()));

        double hcTramosMensuales = organizacion.getMiembros().stream().mapToDouble(c -> this.calcularHCMiembroAnual(c,anio)).sum();

        return (hcTramosMensuales + hcActividadesAnual) / mesActual ; // prorrateo de los meses

        //TODO calculosHcService para persistir los calculos HC
    }

    public double calcularHCSectorPromedioMensual(Sector sector, LocalDate mesElegido) {
        return calcularHCOrganizacionMensual(sector.getOrganizacion(), mesElegido) / sector.getMiembros().size();
    }

    public double calculoHCSectorPromedioAnual(Sector sector, int anio) {
        return calcularHCOrganizacionAnual(sector.getOrganizacion(), anio) / sector.getMiembros().size();
    }

    public double calcularHCSectorTerritorialAnual(SectorTerritorial sectorTerritorial, int anio) {
        return sectorTerritorial.getOrganizaciones().stream()
                .mapToDouble(organizacion -> this.calcularHCOrganizacionAnual(organizacion, anio))
                .sum();
    }

    public double calcularHCSectorTerritorialMensual(SectorTerritorial sectorTerritorial, LocalDate mesElegido) {
        return sectorTerritorial.getOrganizaciones().stream()
                .mapToDouble(organizacion -> this.calcularHCOrganizacionMensual(organizacion, mesElegido))
                .sum();
    }

    public double calcularHCTrayecto(Trayecto trayecto) {
        return trayecto.getTramos().stream()
                .mapToDouble(this::calcularHCTramo)
                .sum();
    }


    public double calcularHCTramoMensual(Tramo tramo) {
        return this.calcularHCTramoSemanal(tramo) * 4.5;
    }

    public double calcularHCTramoSemanal(Tramo tramo) {
        return this.calcularHCTramo(tramo) / tramo.getMiembros().stream().filter(miembro -> miembro.getOrganizacion().getRazonSocial() == organizacionCalculo.getRazonSocial()).count() * organizacionCalculo.getCantDiasPorSemana() * tramo.getPeso(); // con peso
    }

    */

}
