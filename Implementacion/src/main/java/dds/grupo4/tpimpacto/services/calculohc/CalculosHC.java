package dds.grupo4.tpimpacto.services.calculohc;

import dds.grupo4.tpimpacto.entities.BaseEntity;
import dds.grupo4.tpimpacto.entities.medicion.Periodicidad;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Calculos_HC")
@Table(name = "calculos_hc")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CalculosHC  extends BaseEntity {

   private double valorHC ;
   private UnidadCalculoHC unidadCalculoHC ;
   private Periodicidad periodo ;


}
