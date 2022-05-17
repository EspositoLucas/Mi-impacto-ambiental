package dds.grupo4.tpimpacto.entities;

import dds.grupo4.tpimpacto.enums.TipoTransportePublico;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TransportePublico")
@Table(name = "transportes_publicos")
public class TransportePublico extends MedioDeTransporte {

    private TipoTransportePublico tipoTransportePublico;
    private String linea;
    private List<Parada> paradas = new ArrayList<>();

    // Hibernate
    protected TransportePublico() {
    }

    public TransportePublico(TipoTransportePublico tipoTransportePublico, String linea, List<Parada> paradas) {
        this.tipoTransportePublico = tipoTransportePublico;
        this.linea = linea;
        this.paradas = paradas;
    }

    public TipoTransportePublico getTipoTransportePublico() {
        return tipoTransportePublico;
    }

    public void setTipoTransportePublico(TipoTransportePublico tipoTransportePublico) {
        this.tipoTransportePublico = tipoTransportePublico;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }
}
