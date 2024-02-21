package com.example.parqueadero.utils;

public class Parqueadero {
    String nit;
    String nombre;
    String cantidadVendedores;
    String direccion;
    String telefono;

    public Parqueadero(String nit, String nombre, String cantidadVendedores, String direccion, String telefono) {
        this.nit = nit;
        this.nombre = nombre;
        this.cantidadVendedores = cantidadVendedores;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Parqueadero(String nit, String nombre, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidadVendedores() {
        return cantidadVendedores;
    }

    public void setCantidadVendedores(String cantidadVendedores) {
        this.cantidadVendedores = cantidadVendedores;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
