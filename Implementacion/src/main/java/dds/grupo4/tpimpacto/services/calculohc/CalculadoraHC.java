package dds.grupo4.tpimpacto.services.calculohc;

import dds.grupo4.tpimpacto.entities.medicion.Actividad;
import dds.grupo4.tpimpacto.entities.medicion.Medicion;
import dds.grupo4.tpimpacto.entities.organizacion.Miembro;
import dds.grupo4.tpimpacto.entities.organizacion.Organizacion;
import dds.grupo4.tpimpacto.entities.organizacion.Sector;
import dds.grupo4.tpimpacto.entities.sectorTerritorial.SectorTerritorial;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;

public class CalculadoraHC {

    public double calcularHCTramo(Tramo tramo) {
        return tramo.getDistanciaRecorrida() * tramo.getMedioDeTransporte().getFactorDeEmision().getValor();
    }

    public double calcularHCDatoActividad(Medicion medicion) {
        // TODO: ver de donde se saca este dato, porque en teoria depende del MedioDeTransporte pero aca
        //  no tenemos ninguna informacion sobre el MedioDeTransporte utilizado
        double factorDeEmision = 123456789;

        if (medicion.getActividad() == Actividad.LogisticaDeProductosYResiduos) {
            // TODO: ver de donde salen estos numeros (creeria que del Excel, asi que habria que meter la funcionalidad
            //  para leer estos datos
            double distanciaMediaRecorrida = 123456789,
                    pesoTotalTransportado = 123456789;
            return distanciaMediaRecorrida * pesoTotalTransportado * factorDeEmision * medicion.getOrganizacion().getFactorK();
        }

        return medicion.getValor() * factorDeEmision;
    }

    public double calcularHCMiembro(Miembro miembro) {
        return miembro.getTramos().stream()
                .mapToDouble(this::calcularHCTramo)
                .sum();
    }

    public double calcularHCOrganizacionMensual(Organizacion organizacion) {
        double hcTramosMensual = -1;
        double hcActividadesMensual = -1;
        return hcTramosMensual + hcActividadesMensual;
    }

    public double calcularHCOrganizacionAnual(Organizacion organizacion) {
        double hcTramosAnual = -1;
        double hcActividadesAnual = -1;
        return hcTramosAnual + hcActividadesAnual;
    }

    public double calcularHCSectorPromedioMensual(Sector sector) {
        return calcularHCOrganizacionMensual(sector.getOrganizacion()) / sector.getMiembros().size();
    }

    public double calculoHCSectorPromedioAnual(Sector sector) {
        return calcularHCOrganizacionAnual(sector.getOrganizacion()) / sector.getMiembros().size();
    }

    public double calcularHCSectorTerritorialAnual(SectorTerritorial sectorTerritorial) {
        return sectorTerritorial.getOrganizaciones().stream()
                .mapToDouble(this::calcularHCOrganizacionAnual)
                .sum();
    }

    public double calcularHCSectorTerritorialMensual(SectorTerritorial sectorTerritorial) {
        return sectorTerritorial.getOrganizaciones().stream()
                .mapToDouble(this::calcularHCOrganizacionMensual)
                .sum();
    }

    public double calcularHCTrayecto(Trayecto trayecto) {
        return trayecto.getTramos().stream()
                .mapToDouble(this::calcularHCTramo)
                .sum();
    }

}
