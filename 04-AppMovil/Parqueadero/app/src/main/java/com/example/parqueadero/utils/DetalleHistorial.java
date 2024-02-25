package com.example.parqueadero.utils;

public class DetalleHistorial {
    String id;
    String tipoVehiculo;
    String placa;
    String responsable;
    String tarifa;
    String entrada;
    String salida;
    String tiempo;

    public DetalleHistorial(String id, String placa, String entrada, String salida, String tipoVehiculo, String tarifa, String responsable, String tiempo) {
        this.id = id;
        this.placa = placa;
        this.entrada = entrada;
        this.salida = salida;
        this.tipoVehiculo = tipoVehiculo;
        this.tarifa = tarifa;
        this.responsable = responsable;
        this.tiempo = tiempo;
    }

    public DetalleHistorial(String id, String tipoVehiculo, String placa, String responsable, String tarifa, String entrada,String tiempo) {
        this.id = id;
        this.tipoVehiculo = tipoVehiculo;
        this.placa = placa;
        this.responsable = responsable;
        this.tarifa = tarifa;
        this.entrada = entrada;
        this.tiempo = tiempo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
