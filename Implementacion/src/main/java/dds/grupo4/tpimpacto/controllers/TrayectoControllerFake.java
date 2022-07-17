package dds.grupo4.tpimpacto.controllers;

import dds.grupo4.tpimpacto.entities.medioTransporte.MedioDeTransporte;
import dds.grupo4.tpimpacto.entities.trayecto.Direccion;
import dds.grupo4.tpimpacto.entities.trayecto.Tramo;
import dds.grupo4.tpimpacto.entities.trayecto.Trayecto;

import java.util.ArrayList;
import java.util.List;

public class TrayectoControllerFake {
    private Direccion direccionInicio;
    private Direccion direccionDestino;
    private List<Tramo> tramos = new ArrayList<>();

    // TODO: completar dar de alta trayecto

//    public TrayectoControllerFake agregarInicioTrayecto(Direccion inicioDeTrayecto) {
//        this.direccionInicio = inicioDeTrayecto;
//        return this;
//    }
//
//    public TrayectoControllerFake agregarTramo(MedioDeTransporte transporte, Direccion destino) {
//        if (tramos.isEmpty()) {
//            tramos.add(new Tramo(transporte, direccionInicio,direccionDestino));
//        } else {
//            tramos.add(new Tramo(transporte, this.finUltimoTramo(), destino));
//        }
//        return this;
//    }




}
