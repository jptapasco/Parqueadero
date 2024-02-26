package com.example.parqueadero.utils;

public class Tarifa {
    String id;
    String vehiculo;
    String valorTarifa;

    public Tarifa(String id, String vehiculo, String valorTarifa) {
        this.id = id;
        this.vehiculo = vehiculo;
        this.valorTarifa = valorTarifa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getValorTarifa() {
        return valorTarifa;
    }

    public void setValorTarifa(String valorTarifa) {
        this.valorTarifa = valorTarifa;
    }
}
