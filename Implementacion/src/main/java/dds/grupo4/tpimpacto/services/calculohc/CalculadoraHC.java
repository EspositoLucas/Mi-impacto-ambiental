package dds.grupo4.tpimpacto.services.calculohc;

import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CalculadoraHC {

    private Organizacion organizacionCalculo;

    public double calcularHCTramo(Tramo tramo) {
        return tramo.getDistanciaRecorrida() * tramo.getMedioDeTransporte().getFactorDeEmision().getValor();
    }


    public double calcularHCDatoActividad(Medicion medicion) {

        double factorDeEmision = medicion.getTipoConsumo().getFactorDeEmision().getValor(); // el FE depende del tipo de consumo en las actividades menos de logistica
        double factorDeEmisionLogisticaYProductos = medicion.getTipoConsumo().getMedioDeTransporte().getFactorDeEmision().getValor();
        if (medicion.getActividad() == Actividad.LogisticaDeProductosYResiduos) {
            // TODO: ver de donde salen estos numeros (creeria que del Excel, asi que habria que meter la funcionalidad
            //  para leer estos datos
            double distanciaMediaRecorrida = medicion.getTipoConsumo().getDistanciaMediaRecorrida(),
                    pesoTotalTransportado = medicion.getTipoConsumo().getPeso() ;
            return distanciaMediaRecorrida * pesoTotalTransportado * factorDeEmisionLogisticaYProductos * medicion.getOrganizacion().getFactorK();
        }

        return medicion.getValor() * factorDeEmision;
    }

    public double calcularHCMiembroMensual(Miembro miembro,LocalDate mesElegido) {
        organizacionCalculo = miembro.getOrganizacion();
        List<Tramo> tramosEnMesElegido =  miembro.getTramos().stream().filter(t->t.getTrayecto().seRealizaEntre(mesElegido)).collect(Collectors.toList());
         double hcTramosMensual = tramosEnMesElegido.stream().mapToDouble(this::calcularHCTramoMensual).sum();

         return hcTramosMensual ;
    }
    public double calcularHCMiembroAnual(Miembro miembro,int año) {
        List<LocalDate> meses = null;
        for (int i=1; i<= 12; i++ ){
            meses.add( LocalDate.of(año, i,1) );
        }


        return meses.stream().mapToDouble(mes->this.calcularHCMiembroMensual(miembro,mes)).sum();
    }

    //Fijarse la repeticion
    public double impactoHCMiembroMensual(Miembro miembro,LocalDate mesElegido) {
        double calculoHCDeOrg = this.calcularHCOrganizacionMensual(miembro.getOrganizacion(), mesElegido) ;
        double calculoHCMiembroMensual = this.calcularHCMiembroMensual(miembro,mesElegido);
        double impacto = calculoHCMiembroMensual *100 / calculoHCDeOrg  ;
        return  impacto;
    }
    public double impactoHCMiembroAnual(Miembro miembro,int año) {
        double calculoHCDeOrg = this.calcularHCOrganizacionAnual(miembro.getOrganizacion(),año) ;
        double calculoHCMiembroAnual = this.calcularHCMiembroAnual(miembro,año);
        double impacto = calculoHCMiembroAnual *100 / calculoHCDeOrg  ;
        return  impacto;
    }

    public double calcularHCOrganizacionMensual(Organizacion organizacion,LocalDate mesElegido) {
        organizacionCalculo = organizacion;
        List<Tramo> tramosEnMesElegido =  organizacion.getTramosDeMiembros().stream().filter(t->t.getTrayecto().seRealizaEntre(mesElegido)).collect(Collectors.toList());
        double hcTramosMensual = tramosEnMesElegido.stream().mapToDouble(this::calcularHCTramoMensual).sum();
        List<Medicion> actividadesMensuales =  organizacion.getMediciones().stream().filter(m->m.getPeriodicidad().equals("MENSUAL")).collect(Collectors.toList());
        double hcActividadesMensual = actividadesMensuales.stream().mapToDouble((this::calcularHCDatoActividad)).sum();
        return hcTramosMensual + hcActividadesMensual;
    }
        //Revisar lo de las fechas
    public double calcularHCOrganizacionAnual(Organizacion organizacion,int año) {
       List<LocalDate> meses = null;
        for (int i=1; i<= 12; i++ ){
            meses.add( LocalDate.of(año, i,1) );
            }


        return meses.stream().mapToDouble(mes->this.calcularHCOrganizacionMensual(organizacion,mes)).sum();
    }

    public double calcularHCSectorPromedioMensual(Sector sector,LocalDate mesElegido) {
        return calcularHCOrganizacionMensual(sector.getOrganizacion(),mesElegido) / sector.getMiembros().size();
    }

    public double calculoHCSectorPromedioAnual(Sector sector,int anio) {
        return calcularHCOrganizacionAnual(sector.getOrganizacion(),anio) / sector.getMiembros().size();
    }

    public double calcularHCSectorTerritorialAnual(SectorTerritorial sectorTerritorial,int anio) {
        return sectorTerritorial.getOrganizaciones().stream()
                .mapToDouble(organizacion-> this.calcularHCOrganizacionAnual(organizacion,anio))
                .sum();
    }

    public double calcularHCSectorTerritorialMensual(SectorTerritorial sectorTerritorial,LocalDate mesElegido) {
        return sectorTerritorial.getOrganizaciones().stream()
                .mapToDouble(organizacion-> this.calcularHCOrganizacionMensual(organizacion,mesElegido))
                .sum();
    }

    public double calcularHCTrayecto(Trayecto trayecto) {
        return trayecto.getTramos().stream()
                .mapToDouble(this::calcularHCTramo)
                .sum();
    }


    public double calcularHCTramoMensual(Tramo tramo) {
        return this.calcularHCTramoSemanal(tramo) * 4.5 ;
    }
    public double calcularHCTramoSemanal(Tramo tramo) {
        return this.calcularHCTramo(tramo) / tramo.getMiembros().stream().filter(miembro -> miembro.getOrganizacion().getRazonSocial() == organizacionCalculo.getRazonSocial()).count() * tramo.getCantVecesPorSemana(); // sin peso
       // return this.calcularHCTramo(tramo) / tramo.getMiembros().stream().filter(miembro -> miembro.getOrganizacion().getRazonSocial() == organizacionCalculo.getRazonSocial()).count() * tramo.getCantVecesPorSemana() * tramo.getPeso(); // con peso
    }

}
